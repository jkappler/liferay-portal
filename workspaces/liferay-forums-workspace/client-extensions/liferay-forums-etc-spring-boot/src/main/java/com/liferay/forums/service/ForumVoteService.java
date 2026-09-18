/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.forums.service;

import com.liferay.forums.client.LiferayApiClient;
import com.liferay.petra.string.StringBundler;

import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Roselaine Marques
 */
@Service
public class ForumVoteService {

	public void recalculateVoteScore(
		long messageId, String messageERC, long siteId, String authToken) {

		if ((messageId <= 0) || (messageERC == null) || messageERC.isEmpty() ||
			(siteId <= 0)) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to recalculate the vote score for message ",
						messageId, " without a message ERC and a site scope"));
			}

			return;
		}

		try {

			// Relationship fields must be filtered by the related entry's
			// ERC, not its numeric ID, or the OData parser throws
			// "Incompatible types."

			String filter = StringBundler.concat(
				"r_messageVotes_c_c2m0MessageERC eq '", messageERC, "'");

			JSONArray itemsJSONArray = new JSONObject(
				_liferayApiClient.get(
					StringBundler.concat(
						"/o/c/c2m0votes/scopes/", siteId,
						"?fields=value&pageSize=-1&filter=", _encode(filter)),
					authToken)
			).optJSONArray(
				"items"
			);

			int voteScore = 0;

			if (itemsJSONArray != null) {
				for (int i = 0; i < itemsJSONArray.length(); i++) {
					JSONObject itemJSONObject = itemsJSONArray.optJSONObject(i);

					if (itemJSONObject != null) {
						voteScore += itemJSONObject.optInt("value", 0);
					}
				}
			}

			// notificationReplyRecipientIds/notificationMentionRecipientIds
			// are cleared here too as a self-healing safeguard: they are
			// already cleared right after every notification by
			// ForumNotificationService, but if that clear patch ever fails,
			// this one gives a message stale from a failed clear another
			// chance to reset the next time it is voted on.

			_liferayApiClient.patch(
				"/o/c/c2m0messages/" + messageId, authToken,
				new JSONObject(
				).put(
					"notificationMentionRecipientIds", ""
				).put(
					"notificationReplyRecipientIds", ""
				).put(
					"voteScore", voteScore
				).toString());
		}
		catch (Exception exception) {
			_log.error(
				StringBundler.concat(
					"Unable to recalculate the vote score for message ",
					messageId, ": ", exception.getMessage()));
		}
	}

	private String _encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}

	private static final Log _log = LogFactory.getLog(ForumVoteService.class);

	@Autowired
	private LiferayApiClient _liferayApiClient;

}
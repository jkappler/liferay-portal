/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayForm, {ClayInput} from '@clayui/form';
import React, {useState} from 'react';

export default function LogoSelector({
	defaultLogoURL,
	logoURL: initialLogoURL,
	portletNamespace,
}) {
	const [logoURL, setLogoURL] = useState(initialLogoURL);
	const [deleteLogo, setDeleteLogo] = useState(false);

	const onClearImage = () => {
		setLogoURL(defaultLogoURL);
		setDeleteLogo(true);
	};

	return (
		<>
			<ClayInput
				name={`${portletNamespace}deleteLogo`}
				type="hidden"
				value={deleteLogo}
			/>

			<ClayInput
				name={`${portletNamespace}fileEntryId`}
				type="hidden"
				value="fileEntryId"
			/>

			{logoURL ? (
				<img
					alt={Liferay.Language.get('current-image')}
					className="avatar img-fluid mw-100"
					src={logoURL}
				/>
			) : (
				<p className="text-muted">{Liferay.Language.get('none')}</p>
			)}

			<ClayForm.Group>
				<label htmlFor={`${portletNamespace}logoName`}>
					{Liferay.Language.get('logo')}
				</label>

				<div className="d-flex">
					<ClayInput
						className="mr-2"
						id={`${portletNamespace}logoName`}
						readOnly={true}
					/>

					<ClayButtonWithIcon
						aria-label={Liferay.Language.get('change-image')}
						className="edit-logo flex-shrink-0 mr-2"
						displayType="secondary"
						symbol="change"
						title={Liferay.Language.get('change-image')}
					/>

					<ClayButtonWithIcon
						aria-label={Liferay.Language.get('clear-image')}
						className="delete-logo flex-shrink-0"
						disabled={logoURL === defaultLogoURL}
						displayType="secondary"
						onClick={onClearImage}
						symbol="times-circle"
						title={Liferay.Language.get('clear-image')}
					/>
				</div>
			</ClayForm.Group>
		</>
	);
}

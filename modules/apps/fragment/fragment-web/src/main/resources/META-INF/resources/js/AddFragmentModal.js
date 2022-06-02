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

import ClayCard from '@clayui/card';
import ClayForm, {ClayInput} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import {useModal} from '@clayui/modal';
import classNames from 'classnames';
import React, {useState} from 'react';

import {MultiStepModal, MultiStepModalStep} from './MultiStepModal';

const FRAGMENT_TYPES = {
	basic: {
		description: Liferay.Language.get(
			'build-fragments-using-html-css-and-javascript'
		),
		key: 'basic',
		symbol: 'code',
		title: Liferay.Language.get('basic-fragment'),
	},
	form: {
		description: Liferay.Language.get(
			'build-input-fragments-for-forms-using-html-css-and-javascript'
		),
		key: 'form',
		symbol: 'forms',
		title: Liferay.Language.get('form-fragment'),
	},
};

function getFieldName(namespace, fieldName) {
	return namespace.concat(fieldName);
}

export default function AddFragmentModal(props) {
	const {addFragmentEntryURL, fieldTypes, portletNamespace} = props;

	const [name, setName] = useState(null);
	const [type, setType] = useState(FRAGMENT_TYPES.basic.key);

	const [visible, setVisible] = useState(true);
	const {observer, onClose} = useModal({
		onClose: () => setVisible(false),
	});

	return (
		<>
			{visible && (
				<MultiStepModal
					className="add-fragment-modal"
					observer={observer}
					onClose={onClose}
					submitLabel={Liferay.Language.get('add')}
					submitURL={addFragmentEntryURL}
					title={Liferay.Language.get('add-fragment')}
				>
					<MultiStepModalStep>
						<p className="font-weight-semi-bold mb-4 text-secondary">
							{Liferay.Language.get('select-fragment-type')}
						</p>

						<div className="d-flex">
							<FragmentTypeCard
								className="fragment-type-card-basic"
								onSelect={setType}
								selectedType={type}
								type={FRAGMENT_TYPES.basic}
							/>

							<FragmentTypeCard
								className="fragment-type-card-form"
								onSelect={setType}
								selectedType={type}
								type={FRAGMENT_TYPES.form}
							/>
						</div>

						<input
							hidden
							name={getFieldName(portletNamespace, 'type')}
							readOnly
							value={3}
						/>
					</MultiStepModalStep>

					<MultiStepModalStep>
						<ClayForm.Group>
							<label htmlFor={`${portletNamespace}name`}>
								{Liferay.Language.get('fragment-name')}
							</label>

							<ClayInput
								id={`${portletNamespace}name`}
								name={getFieldName(portletNamespace, 'name')}
								onChange={(event) =>
									setName(event.target.value)
								}
								required
								type="text"
								value={name || ''}
							/>
						</ClayForm.Group>
					</MultiStepModalStep>
				</MultiStepModal>
			)}
		</>
	);
}

function FragmentTypeCard({className, onSelect, selectedType, type}) {
	const {description, key, symbol, title} = type;

	return (
		<ClayCard
			active={selectedType === key}
			className={classNames('fragment-type-card mb-0 w-50', className)}
			onClick={() => onSelect(key)}
			selectable
		>
			<ClayCard.AspectRatio className="card-item-first">
				<div className="aspect-ratio-item aspect-ratio-item-center-middle card-type-asset-icon">
					<ClayIcon className="text-white" symbol={symbol} />
				</div>
			</ClayCard.AspectRatio>

			<ClayCard.Body>
				<ClayCard.Row>
					<div className="autofit-col autofit-col-expand">
						<section className="autofit-section">
							<ClayCard.Description displayType="title">
								{title}
							</ClayCard.Description>

							<ClayCard.Description
								displayType="text"
								truncate={false}
							>
								{description}
							</ClayCard.Description>
						</section>
					</div>
				</ClayCard.Row>
			</ClayCard.Body>
		</ClayCard>
	);
}

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

import ClayButton from '@clayui/button';
import ClayForm from '@clayui/form';
import ClayModal from '@clayui/modal';
import {fetch, navigate, openToast} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

export function MultiStepModal({
	children,
	className,
	observer,
	onClose,
	size,
	submitLabel = Liferay.Language.get('submit'),
	submitURL,
	title,
}) {
	const [currentStepIndex, setCurrentStepIndex] = useState(0);

	const maxSteps = React.Children.count(children);

	const isPreviousButtonEnabled = currentStepIndex > 0;
	const isNextButtonEnabled = currentStepIndex < maxSteps - 1;

	const handlePreviousStepButtonClick = () => {
		setCurrentStepIndex((previousIndex) => previousIndex - 1);
	};

	const handleNextStepButtonClick = () => {
		setCurrentStepIndex((previousIndex) => previousIndex + 1);
	};

	const handleFormSubmit = (event) => {
		const formData = new FormData(event.target);

		event.preventDefault();

		fetch(submitURL, {
			body: formData,
			method: 'POST',
		})
			.then((response) => response.json())
			.then((response) => {
				if (response.redirectURL) {
					navigate(response.redirectURL);
				}
			})
			.catch(() => {
				openToast({
					message: Liferay.Language.get(
						'an-unexpected-error-occurred'
					),
					type: 'danger',
				});
			});
	};

	return (
		<ClayModal className={className} observer={observer} size={size}>
			<ClayForm action={submitURL} onSubmit={handleFormSubmit}>
				{title && <ClayModal.Header>{title}</ClayModal.Header>}

				<ClayModal.Body>
					{React.Children.map(children, (child, index) =>
						React.cloneElement(child, {
							isActive: index === currentStepIndex,
						})
					)}
				</ClayModal.Body>

				<ClayModal.Footer
					last={
						<ClayButton.Group spaced>
							<ClayButton
								displayType="secondary"
								onClick={
									isPreviousButtonEnabled
										? handlePreviousStepButtonClick
										: onClose
								}
							>
								{isPreviousButtonEnabled
									? Liferay.Language.get('previous')
									: Liferay.Language.get('cancel')}
							</ClayButton>

							<ClayButton
								displayType="primary"
								onClick={
									isNextButtonEnabled
										? handleNextStepButtonClick
										: null
								}
								type={isNextButtonEnabled ? 'button' : 'submit'}
							>
								{isNextButtonEnabled
									? Liferay.Language.get('next')
									: submitLabel}
							</ClayButton>
						</ClayButton.Group>
					}
				/>
			</ClayForm>
		</ClayModal>
	);
}

MultiStepModal.propTypes = {
	className: PropTypes.string,
	observer: PropTypes.object.isRequired,
	onClose: PropTypes.func.isRequired,
	size: PropTypes.string,
	submitLabel: PropTypes.string,
	submitURL: PropTypes.string.isRequired,
	title: PropTypes.string,
};

export function MultiStepModalStep({children, isActive}) {
	return (
		<div aria-hidden={!isActive} style={{display: isActive ? '' : 'none'}}>
			{children}
		</div>
	);
}

MultiStepModalStep.propTypes = {
	isActive: PropTypes.bool,
};

create table MSBPageTemplate (
	uuid_ VARCHAR(75) null,
	msbPageTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	msbPageTemplateFolderId LONG
);

create table MSBPageTemplateFolder (
	uuid_ VARCHAR(75) null,
	msbPageTemplateFolderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);

create table MSBPageTemplate (
	uuid_ VARCHAR(75) null,
	msbPageTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	msbPageTemplateFolderId LONG
);

create table MSBPageTemplateFolder (
	uuid_ VARCHAR(75) null,
	msbPageTemplateFolderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	description VARCHAR(75) null
);
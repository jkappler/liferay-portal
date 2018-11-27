create table AssetDisplayPageEntry (
	uuid_ VARCHAR(75) null,
	assetDisplayPageEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	layoutPageTemplateEntryId LONG,
	type_ INTEGER
);

create index IX_31FA120C on AssetDisplayPageEntry (classNameId, classPK);
create unique index IX_A21FC9A8 on AssetDisplayPageEntry (groupId, classNameId, classPK);
create index IX_BFB8A913 on AssetDisplayPageEntry (layoutPageTemplateEntryId);
create index IX_1DA6952B on AssetDisplayPageEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DB986A6D on AssetDisplayPageEntry (uuid_[$COLUMN_LENGTH:75$], groupId);

COMMIT_TRANSACTION;
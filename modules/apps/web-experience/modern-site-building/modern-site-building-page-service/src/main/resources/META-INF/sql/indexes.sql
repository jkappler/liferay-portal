create index IX_98614174 on MSBPageTemplate (msbPageTemplateFolderId, name[$COLUMN_LENGTH:75$]);
create index IX_2B1CE654 on MSBPageTemplate (name[$COLUMN_LENGTH:75$], msbPageTemplateFolderId);
create index IX_EB6B055F on MSBPageTemplate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B06357A1 on MSBPageTemplate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_9430C650 on MSBPageTemplateFolder (groupId, name[$COLUMN_LENGTH:75$]);
create index IX_F3E80B2D on MSBPageTemplateFolder (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B86378EF on MSBPageTemplateFolder (uuid_[$COLUMN_LENGTH:75$], groupId);
package su.sergey.contacts.directory.commands;

import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

abstract class DefaultDirectoryCommand extends AbstractCommand {
    protected DirectoryMetadata getDirectoryMetadata(DirectoryHttpServletRequest request, DirectoryMetadataHandle handle)
            throws ContactsException {
        return getDAOBusinessDelegate(request.getRequest()).findDirectoryMetadata(handle);
    }
}

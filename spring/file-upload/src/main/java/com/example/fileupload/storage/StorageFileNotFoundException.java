package com.example.fileupload.storage;

public class StorageFileNotFoundException extends StorageException {

    private static final long serialVersionUID = 1L;

    public StorageFileNotFoundException(final String message) {
        super(message);
    }

    public StorageFileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

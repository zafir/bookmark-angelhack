package com.example.zafir.myapplication;

import android.provider.BaseColumns;

public final class BookmarksDbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public BookmarksDbContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Book implements BaseColumns {
        public static final String TABLE_NAME = "books";
        public static final String COLUMN_NAME_BOOK_ID = "bookid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PAGES = "pages";
        public static final String COLUMN_NAME_ISBN = "isbn";
        public static final String COLUMN_NAME_THUMBNAIL = "thumbnail";
    }
}

package com.openclassrooms.savemytrip;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.room.Room;
import androidx.test.platform.app.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.savemytrip.database.SaveMyTripDatabase;
import com.openclassrooms.savemytrip.models.User;
import com.openclassrooms.savemytrip.provider.ItemContentProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Philippe on 01/03/2018.
 */

@RunWith(AndroidJUnit4.class)
public class ItemContentProviderTest {

    // FOR DATA
    private ContentResolver mContentResolver;

    // DATA SET FOR TEST
    private static final long USER_ID = 1;
    private static final User USER_DEMO = new User(USER_ID, "Philippe", "https://www.google.fr, ");

    @Before
    public void setUp() {
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                SaveMyTripDatabase.class)
                .allowMainThreadQueries()
                .build();
        mContentResolver = InstrumentationRegistry.getInstrumentation().getContext().getContentResolver();
    }

    @Test
    public void getItemsWhenNoItemInserted() {
        final Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(ItemContentProvider.URI_ITEM, USER_ID), null, null, null, null);
        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(0));
        cursor.close();
    }

    @Test
    public void insertAndGetItem() {
        // BEFORE : Adding demo item
        final Uri userUri = mContentResolver.insert(ItemContentProvider.URI_ITEM, generateItem());
        // TEST
        final Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(ItemContentProvider.URI_ITEM, USER_ID), null, null, null, null);
        assertThat(cursor, notNullValue());
        assertThat(cursor.getCount(), is(1));
        assertThat(cursor.moveToFirst(), is(true));
        assertThat(cursor.getString(cursor.getColumnIndexOrThrow("text")), is("Visite cet endroit de rêve !"));
    }

    // ---

    private ContentValues generateItem(){
        final ContentValues values = new ContentValues();
        values.put("text", "Visite cet endroit de rêve !");
        values.put("category", "0");
        values.put("isSelected", "false");
        values.put("user_id", USER_DEMO.getId());
        values.put("user_username", USER_DEMO.getUsername());
        values.put("user_urlPicture", USER_DEMO.getUrlPicture());
        return values;
    }
}

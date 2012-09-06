/*******************************************************************************
 * Copyright (C) 2005-2012 Alfresco Software Limited.
 * 
 * This file is part of the Alfresco Mobile SDK.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
/*
 * Copyright (C) 2010 Florian Maul & Jean Marie PASCAL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.mobile.android.application.accounts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AccountSchema
{

    public static final String TABLENAME = "Account";

    public static final String COLUMN_ID = "id";

    public static final int COLUMN_ID_ID = 0;

    public static final String COLUMN_NAME = "name";

    public static final int COLUMN_NAME_ID = 1;

    public static final String COLUMN_URL = "url";

    public static final int COLUMN_URL_ID = 2;

    public static final String COLUMN_USERNAME = "username";

    public static final int COLUMN_USERNAME_ID = 3;

    public static final String COLUMN_PASSWORD = "password";

    public static final int COLUMN_PASSWORD_ID = 4;

    public static final String COLUMN_REPOSITORY_ID = "repositoryId";

    public static final int COLUMN_REPOSITORY_ID_ID = 5;

    public static final String COLUMN_REPOSITORY_TYPE = "repositoryTypeId";

    public static final int COLUMN_REPOSITORY_TYPE_ID = 6;
    
    public static final String COLUMN_ACTIVATION = "activation";

    public static final int COLUMN_ACTIVATION_ID = 7;


    private static final String QUERY_TABLE_CREATE = "create table " + TABLENAME + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT NOT NULL," + COLUMN_URL + " TEXT NOT NULL,"
            + COLUMN_USERNAME + " TEXT NOT NULL," + COLUMN_PASSWORD + " TEXT NOT NULL," + COLUMN_REPOSITORY_ID + " TEXT NOT NULL,"
            + COLUMN_REPOSITORY_TYPE + " INTEGER," + COLUMN_ACTIVATION + " TEXT);";

    private static final String QUERY_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLENAME;

    public static void onCreate(Context context, SQLiteDatabase db)
    {
        db.execSQL(AccountSchema.QUERY_TABLE_CREATE);
        
        AccountDAO serverDao = new AccountDAO(context, db);
        //serverDao.insert("TEAM", "http://alfresco.teamhosted.com:80/alfresco/service/cmis", "ipaduser", "1padu$er2","Main Repository", Integer.valueOf(SessionSettings.BINDING_TYPE_ALFRESCO_CMIS)); 
        //serverDao.insert("TS", "https://ts.alfresco.com/alfresco/service/cmis", "mdubresson", "password","Main Repository", Integer.valueOf(SessionSettings.BINDING_TYPE_ALFRESCO_CMIS)); 
        //serverDao.insert("192.168.1.68", "http://192.168.1.68:8080/alfresco/service/cmis", "admin", "admin","Main Repository", Integer.valueOf(SessionSettings.BINDING_TYPE_ALFRESCO_CMIS)); 
    }

    public static void onUpgrade(Context context, SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion == 1)
        {
            db.execSQL(QUERY_TABLE_DROP);
            onCreate(context, db);
        }
    }
}

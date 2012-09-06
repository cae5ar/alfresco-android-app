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
package org.alfresco.mobile.android.application;

import java.util.List;

import org.alfresco.mobile.android.application.accounts.Account;
import org.alfresco.mobile.android.application.accounts.fragment.AccountsLoader;
import org.alfresco.mobile.android.application.accounts.fragment.CreateAccountDialogFragment;
import org.alfresco.mobile.android.application.accounts.fragment.SignupCloudDialogFragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;

public class HomeScreenActivity extends Activity implements LoaderCallbacks<List<Account>>
{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdkapp_homescreen);
        getActionBar().hide();
        getLoaderManager().restartLoader(AccountsLoader.ID, null, this);
        getLoaderManager().getLoader(AccountsLoader.ID).forceLoad();

        findViewById(R.id.master_pane).setVisibility(View.GONE);
    }
    
    public void cloud(View v){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(SignupCloudDialogFragment.TAG);
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        SignupCloudDialogFragment newFragment = new SignupCloudDialogFragment();
        newFragment.show(ft, SignupCloudDialogFragment.TAG);
    }

    public void launch(View v)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(CreateAccountDialogFragment.TAG);
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        CreateAccountDialogFragment newFragment = new CreateAccountDialogFragment();
        newFragment.show(ft, CreateAccountDialogFragment.TAG);
    }

    @Override
    public Loader<List<Account>> onCreateLoader(int id, Bundle args)
    {
        return new AccountsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Account>> arg0, List<Account> results)
    {
        if (results != null && results.size() > 0)
        {
            Intent i = new Intent(this, MainActivity.class);
            this.startActivity(i);
        } else {
            findViewById(R.id.master_pane).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Account>> arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}

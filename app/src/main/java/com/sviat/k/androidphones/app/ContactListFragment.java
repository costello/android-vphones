package com.sviat.k.androidphones.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sviat.k.androidphones.app.activity.ContactDetailActivity;
import com.sviat.k.androidphones.app.contacts.ContactDatabase;
import com.sviat.k.androidphones.app.contacts.ContactRecord;

import java.util.ArrayList;

/**
 * Created by Sviat on 04.11.14.
 */
public class ContactListFragment extends ListFragment {
    private static final String TAG = "PhonesListFragment";

    private ArrayList<ContactRecord> mContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContacts = ContactDatabase.get(getActivity()).getContacts();

        ContactListAdapter adapter = new ContactListAdapter(mContacts);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ContactRecord cr = (ContactRecord) getListAdapter().getItem(position);
        Intent intentContactDetails = new Intent(getActivity(), ContactDetailActivity.class);

        intentContactDetails.putExtra(ContactDetailFragment.EXTRA_CONTACT_ID, cr.getId());
        startActivity(intentContactDetails);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ContactListAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private class ContactListAdapter extends ArrayAdapter<ContactRecord> {
        public ContactListAdapter(ArrayList<ContactRecord> contacts) {
            super(getActivity(), 0, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_contact, null);
            }

            ContactRecord cd = (ContactRecord) getListAdapter().getItem(position);

            TextView contactName = (TextView) convertView.findViewById(R.id.contact_Name);
            TextView contactLastCallTime = (TextView) convertView.findViewById(R.id.contact_LastCallTime);

            contactName.setText(cd.getDisplayName());
            contactLastCallTime.setText(cd.getLastCall());

            return convertView;
        }
    }
}
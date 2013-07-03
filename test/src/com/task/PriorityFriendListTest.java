package com.task;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.task.json.Friend;
import com.task.json.GsonHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowListView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Leus Artem
 * @since 15.06.13
 */
@RunWith(RobolectricTestRunner.class)
public class PriorityFriendListTest {

    MainActivity activity;
    FriendListFragment friendListFragment;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().start().resume().get();

        friendListFragment = new FriendListFragment();
        activity.getSupportFragmentManager().beginTransaction().add(friendListFragment, null).commit();
    }

    @Test
    public void jsonHelperTest(){
        List<Friend> friendList = new ArrayList<Friend>(){{
            add(new Friend("000", 23));
            add(new Friend("id", 0));
        }};
        String json = GsonHelper.serialize(friendList);
        Assert.assertTrue(GsonHelper.deserialize(json).equals(friendList));
    }

    /*@Test
    public void hasPrioritySpinnerTest(){
        ListView listView = friendListFragment.getListView();
        assertNotNull(listView);
        FriendListFragment.FriendListAdapter adapter = (FriendListFragment.FriendListAdapter) listView.getAdapter();
        assertNotNull(adapter);
        adapter.add(TestConstants.MOCK_USER);

        View listRowView = adapter.getView(0, null, null);
        assertNotNull(listRowView);
        assertNotNull(listRowView.findViewById(R.id.priority_spinner));
    }*/
}

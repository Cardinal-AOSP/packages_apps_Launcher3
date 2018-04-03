package com.google.android.apps.nexuslauncher;

import android.view.View;

import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.graphics.DrawableFactory;
import com.android.launcher3.popup.SystemShortcut;

public class CustomEditShortcut extends SystemShortcut {
    public CustomEditShortcut() {
        super(R.drawable.ic_edit_no_shadow, R.string.action_edit);
    }

    @Override
    public View.OnClickListener getOnClickListener(final Launcher launcher, final ItemInfo itemInfo) {
        if (CustomIconUtils.usingValidPack(launcher)) {
            CustomDrawableFactory factory = (CustomDrawableFactory) DrawableFactory.get(launcher);
            factory.ensureInitialLoadComplete();

            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AbstractFloatingView.closeAllOpenViews(launcher);
                    CustomBottomSheet cbs = (CustomBottomSheet) launcher.getLayoutInflater()
                        .inflate(R.layout.app_edit_bottom_sheet, launcher.getDragLayer(), false);
                    cbs.populateAndShow(itemInfo);
                }
            };
        }

        return null;
    }
}

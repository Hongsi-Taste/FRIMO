/*
 * Copyright (C) 2017 skydoves
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

package com.example.frimo.hamburgermenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.example.frimo.R;
import com.skydoves.powermenu.CircularEffect;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

public class PowerMenuUtils {

  public static PowerMenu getHamburgerPowerMenu(
      Context context,
      LifecycleOwner lifecycleOwner,
      OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener,
      OnDismissedListener onDismissedListener) {
    return new PowerMenu.Builder(context)
        .addItem(new PowerMenuItem("Friendly Mode", true))
        .addItem(new PowerMenuItem("Secret Mode", false))
        .addItem(new PowerMenuItem("Gallery Mode", false))
        .setAutoDismiss(true)
        .setLifecycleOwner(lifecycleOwner)
        .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT) // popup시 menu가 뜨는 위치
        .setCircularEffect(CircularEffect.BODY)
        .setMenuRadius(10f)
        .setMenuShadow(10f)
        .setTextColor(Color.BLACK)
        .setTextSize(12)
        .setTextGravity(Gravity.CENTER)
        .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD)) // 서체, 글꼴
        .setSelectedTextColor(Color.WHITE)
        .setMenuColor(Color.WHITE)
        .setSelectedMenuColor(ContextCompat.getColor(context, R.color.purple_200))
        .setOnMenuItemClickListener(onMenuItemClickListener)
        .setOnDismissListener(onDismissedListener)
        .setPreferenceName("HamburgerPowerMenu")
        .setInitializeRule(Lifecycle.Event.ON_CREATE, 0)
        .build();
  }

}

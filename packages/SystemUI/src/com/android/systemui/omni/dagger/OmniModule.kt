/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.omni.dagger

import com.android.systemui.qs.QsEventLogger
import com.android.systemui.qs.pipeline.shared.TileSpec
import com.android.systemui.qs.shared.model.TileCategory
import com.android.systemui.qs.tiles.AODTile
import com.android.systemui.qs.tiles.CaffeineTile
import com.android.systemui.qs.tiles.DataSwitchTile
import com.android.systemui.qs.tiles.ScreenshotTile
import com.android.systemui.qs.tiles.base.shared.model.QSTileConfig
import com.android.systemui.qs.tiles.base.shared.model.QSTileUIConfig

import com.android.systemui.qs.tileimpl.QSTileImpl
import com.android.systemui.res.R

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey


/** Dagger Module for code in the systemui package. */
@Module
interface OmniModule {
    /** Inject Tiles */
    @Binds
    @IntoMap
    @StringKey(AODTile.TILE_SPEC)
    fun bindAODTile(aodTile: AODTile): QSTileImpl<*>

    @Binds
    @IntoMap
    @StringKey(CaffeineTile.TILE_SPEC)
    fun bindCaffeineTile(caffeineTile: CaffeineTile): QSTileImpl<*>

    @Binds
    @IntoMap
    @StringKey(DataSwitchTile.TILE_SPEC)
    fun bindDataSwitchTile(dataSwitchTile: DataSwitchTile): QSTileImpl<*>

    @Binds
    @IntoMap
    @StringKey(ScreenshotTile.TILE_SPEC)
    fun bindScreenshotTile(screenshotTile: ScreenshotTile): QSTileImpl<*>

    companion object {
      const val AOD_TILE_SPEC = "aod"
      const val CAFFEINE_TILE_SPEC = "caffeine"
      const val DATASWITCH_TILE_SPEC = "dataswitch"
      const val SCREENSHOT_TILE_SPEC = "screenshot"

      @Provides
      @IntoMap
      @StringKey(AOD_TILE_SPEC)
      fun provideAodTileConfig(uiEventLogger: QsEventLogger): QSTileConfig =
          QSTileConfig(
              tileSpec = TileSpec.create(AOD_TILE_SPEC),
              uiConfig =
                  QSTileUIConfig.Resource(
                      iconRes = R.drawable.ic_qs_aod,
                      labelRes = R.string.quick_settings_aod_label
                  ),
              instanceId = uiEventLogger.getNewInstanceId(),
              category = TileCategory.DISPLAY,
          )

      @Provides
      @IntoMap
      @StringKey(CAFFEINE_TILE_SPEC)
      fun provideCaffeineTileConfig(uiEventLogger: QsEventLogger): QSTileConfig =
          QSTileConfig(
              tileSpec = TileSpec.create(CAFFEINE_TILE_SPEC),
              uiConfig =
                  QSTileUIConfig.Resource(
                      iconRes = R.drawable.ic_qs_caffeine,
                      labelRes = R.string.quick_settings_caffeine_label
                  ),
              instanceId = uiEventLogger.getNewInstanceId(),
              category = TileCategory.DISPLAY,
          )

      @Provides
      @IntoMap
      @StringKey(DATASWITCH_TILE_SPEC)
      fun provideDataSwitchTileConfig(uiEventLogger: QsEventLogger): QSTileConfig =
          QSTileConfig(
              tileSpec = TileSpec.create(DATASWITCH_TILE_SPEC),
              uiConfig =
                  QSTileUIConfig.Resource(
                      iconRes = R.drawable.ic_qs_data_switch_1,
                      labelRes = R.string.qs_data_sim_1
                  ),
              instanceId = uiEventLogger.getNewInstanceId(),
              category = TileCategory.CONNECTIVITY,
          )

      @Provides
      @IntoMap
      @StringKey(SCREENSHOT_TILE_SPEC)
      fun provideScreenShotTileConfig(uiEventLogger: QsEventLogger): QSTileConfig =
          QSTileConfig(
              tileSpec = TileSpec.create(SCREENSHOT_TILE_SPEC),
              uiConfig =
                  QSTileUIConfig.Resource(
                      iconRes = com.android.internal.R.drawable.ic_screenshot,
                      labelRes = R.string.global_action_screenshot
                  ),
              instanceId = uiEventLogger.getNewInstanceId(),
              category = TileCategory.DISPLAY,
          )
    }
}

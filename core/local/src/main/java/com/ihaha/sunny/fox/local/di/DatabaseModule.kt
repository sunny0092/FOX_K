/*
 * Designed and developed by 2019 skydoves (Jaewoong Eum)
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

package com.ihaha.sunny.fox.local.di

import androidx.room.Room
import com.ihaha.sunny.fox.local.AppDatabase
import com.ihaha.sunny.fox.local.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
  single {
    Room
      .databaseBuilder(
        androidApplication(),
        AppDatabase::class.java,
        BuildConfig.FOX_DATABASE_NAME)
      .allowMainThreadQueries()
      .fallbackToDestructiveMigration()
      .build()
  }

  single { get<AppDatabase>().newestDao() }
  single { get<AppDatabase>().videosDao() }
  single { get<AppDatabase>().trendingDao() }
  single { get<AppDatabase>().editorsChoiceDao() }
  single { get<AppDatabase>().tagsDao()}
  single { get<AppDatabase>().seriesDao()}
  single { get<AppDatabase>().organizationsDao()}
}

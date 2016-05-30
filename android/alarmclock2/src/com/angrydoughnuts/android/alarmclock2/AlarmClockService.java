/****************************************************************************
 * Copyright 2016 kraigs.android@gmail.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ****************************************************************************/

package com.angrydoughnuts.android.alarmclock2;

import android.app.Service;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.TimeZone;

public class AlarmClockService extends Service {
  public static final String ALARM_ID = "alarm_id";

  private static final String TAG = AlarmClockService.class.getSimpleName();

  @Override
  public IBinder onBind(Intent intent) {
    return new IdentityBinder();
  }

  public void createTestAlarm() {
    final long tsUTC = System.currentTimeMillis() + 5000;

    final Calendar c = Calendar.getInstance();
    c.setTimeInMillis(tsUTC);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);

    AlarmNotificationService.newAlarm(
        this, (int)(tsUTC - c.getTimeInMillis()) / 1000);
  }

  public class IdentityBinder extends Binder {
    public AlarmClockService getService() {
      return AlarmClockService.this;
    }
  }
}
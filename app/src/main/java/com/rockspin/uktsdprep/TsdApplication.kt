package com.rockspin.uktsdprep

import android.app.Application
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class TsdApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OssLicensesMenuActivity.setActivityTitle(getString(R.string.title_oss_licenses))
    }
}
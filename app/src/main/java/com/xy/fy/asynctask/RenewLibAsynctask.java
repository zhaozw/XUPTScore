package com.xy.fy.asynctask;


import top.codemc.common.util.StaticVarUtil;
import top.codemc.common.util.Util;
import top.codemc.common.util.ViewUtil;
import top.codemc.rpcapi.HttpUtilMc;

import com.xy.fy.adapter.LibAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class RenewLibAsynctask extends AsyncTask<String, String, String> {

    ProgressDialog progressDialog;
    Activity mActivity;
    private OnRenew onRenew;

    public RenewLibAsynctask(Activity mActivity, OnRenew onRenew) {
        // TODO Auto-generated constructor stub
        this.mActivity = mActivity;
        this.progressDialog = ViewUtil.getProgressDialog(mActivity, "���������");
        progressDialog.show();
        this.onRenew = onRenew;
    }

    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        Util.getRenewParmas(params[0]);
        String url = HttpUtilMc.LIB_URL + "/servlet/RenewServlet?data="
                + StaticVarUtil.renewData
                + "&viewstate=" + StaticVarUtil.renewViewstate;
        return HttpUtilMc.queryStringForPost(url);
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        onRenew.onRenew(result, progressDialog);

    }

    private void ShowLibMessage(LibAdapter adapter) {
        adapter.notifyDataSetChanged();
    }


    public interface OnRenew {
        void onRenew(String result, ProgressDialog progressDialog);
    }
}

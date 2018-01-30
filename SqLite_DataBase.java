package com.luminous.eshopfloor.mr_aproval;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by lenovo on 24-09-2017.
 */

public class SqLite_DataBase extends SQLiteOpenHelper {


    private static final String TAG = SqLite_DataBase.class.getName();
    private static SqLite_DataBase mInstance = null;
    private  Context context;

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "eShopFloor";


    // Adv Table List
    private  static  final String Table_Case_List_Live = "eShopFloor_MR_Detail";
   





    // Case List Table Field details
    private  static  final String localID = "localID";
    private  static  final String Reservation_No = "Reservation_No";
    private  static  final String Line_Item = "Line_Item";
    private  static  final String Requirement_Type = "Requirement_Type";
    private  static  final String Delivery_Date = "Delivery_Date";
    private  static  final String Usr_Name = "Usr_Name";
    private  static  final String Cost_Center = "Cost_Center";
    private  static  final String Material = "Material";
    private  static  final String Material_Description = "Material_Description";
    private  static  final String Material_Type = "Material_Type";
    private  static  final String Plant = "Plant";
    private  static  final String Storage_Location = "Storage_Location";
    private  static  final String Requirement_Qty = "Requirement_Qty";
    private  static  final String Wip_Qty = "Wip_Qty";
    private  static  final String Material_Cost = "Material_Cost";
    private  static  final String Base_UOM = "Base_UOM";
    private  static  final String Qty_withdrawn = "Qty_withdrawn";
    private  static  final String Production_Order_No = "Production_Order_No";
    private  static  final String Movement_Type = "Movement_Type";
    private  static  final String Goods_Recipient = "Goods_Recipient";

 /*   private  static  final String Production_Order_No = "Production_Order_No";
    private  static  final String Production_Order_No = "Production_Order_No";
    private  static  final String Production_Order_No = "Production_Order_No";*/

    private  static  final String Add_Update_Status_CaseList = "Add_Update_Status";


    String CREATE_TABLE_MR = "CREATE TABLE " + Table_Case_List_Live + "("+ localID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Reservation_No + " TEXT,"
            + Line_Item + " TEXT,"
            + Requirement_Type + " TEXT,"
            + Delivery_Date + " TEXT,"
            + Usr_Name + " TEXT,"
            + Cost_Center + " TEXT,"
            + Material + " TEXT,"
            + Material_Description + " TEXT,"
            + Material_Type + " TEXT,"
            + Plant + " TEXT,"
            + Storage_Location + " TEXT,"
            + Wip_Qty + " TEXT,"
            + Material_Cost + " TEXT,"
            + Base_UOM + " TEXT,"
            + Qty_withdrawn + " TEXT,"
            + Production_Order_No + " TEXT,"
            + Movement_Type + " TEXT,"
            + Goods_Recipient + " TEXT,"
            + Add_Update_Status_CaseList + " TEXT" + ")";






    public static synchronized SqLite_DataBase getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new SqLite_DataBase(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public SqLite_DataBase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_MR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Case_List_Live);
        onCreate(db);
    }


    
    
    
    
    
    

    public  long AddUpdate_CaseList_Live(String strDateKey , String GetParrentKey  , String  currentkeyAtual  , String AddDate , String Add_Update_Status , String  locallocalID)
    {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();


                values.put(Reservation_No, strDateKey);
                values.put(Line_Item, strDateKey);
                values.put(Requirement_Type, strDateKey);
                values.put(Delivery_Date, strDateKey);
                values.put(Usr_Name, strDateKey);
                values.put(Cost_Center, strDateKey);
                values.put(Material, strDateKey);
                values.put(Material_Description, strDateKey);
                values.put(Material_Type, strDateKey);
                values.put(Plant, strDateKey);
                values.put(Storage_Location, strDateKey);
                values.put(Wip_Qty, strDateKey);
                values.put(Material_Cost, strDateKey);
                values.put(Base_UOM, strDateKey);

                values.put(Qty_withdrawn, strDateKey);
                values.put(Production_Order_No, strDateKey);
                values.put(Movement_Type, strDateKey);
                values.put(Goods_Recipient, strDateKey);
                values.put(Add_Update_Status_CaseList, Add_Update_Status);

            Log.d("AddUpdate "," Need to Add");
            long    aa = db.insert(Table_Case_List_Live, null, values);

            Log.e("JSON Parser", "driverID  RideTrackerData   " + aa);
            db.close();
        return aa;
    }


  



    void DeleteAllCaseListData_LiveTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Table_Case_List_Live);
        db.close();
    }

    public  String getCaseListDataAll()
    {
        String aaa = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_Case_List_Live;
        Cursor cr  = db.rawQuery(selectQuery, null);
        if (cr != null &&  cr.moveToFirst() ) {
            do{
                aaa = aaa +"----"+cr.getString(2);
            }
            while (cr.moveToNext());
        }
        db.close();
        return aaa;
    }

    public   Cursor getCaseListDataAll_NOtAddlist(String Addlist)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_Case_List_Live +" WHERE "+Add_Update_Status_CaseList+" !='"+Addlist+"'";
        Cursor cr  = db.rawQuery(selectQuery, null);
        if (cr != null) {
            do{

            }
            while (cr.moveToNext());
        }
        db.close();
        return cr;
    }
    public   Cursor getCaseListDataAll_WithKeyAndStatus(String GetParrentKey , String add_Update_Status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Table_Case_List_Live+" WHERE "+Reservation_No+" ='"+GetParrentKey+"' AND add_Update_Status !='"+add_Update_Status+"'";
        Cursor cr  = db.rawQuery(selectQuery, null);
        if (cr != null) {
            do{

            }
            while (cr.moveToNext());
        }
        db.close();
        return cr;
    }

    public  long deleteCaseFromLocalLiveTable(String isFrom , String localCashID)
    {
            long deleteStatus=0;
            SQLiteDatabase db = this.getWritableDatabase();
            deleteStatus = db.delete(Table_Case_List_Live, localID + " = ?",new String[] { localCashID });
        return deleteStatus;
    }
    
    
    
}

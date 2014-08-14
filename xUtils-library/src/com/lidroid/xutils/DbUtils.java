/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lidroid.xutils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.text.TextUtils;

import com.lidroid.xutils.db.sqlite.DbModelSelector;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.SqlInfo;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;

public  abstract class DbUtils {
	public abstract DbUtils configDebug(boolean debug);

    public abstract DbUtils configAllowTransaction(boolean allowTransaction);
    //*********************************************** operations ********************************************************

    public abstract void saveOrUpdate(Object entity) throws DbException ;

    public abstract void saveOrUpdateAll(List<?> entities) throws DbException ;

    public abstract void replace(Object entity) throws DbException ;

    public abstract void replaceAll(List<?> entities) throws DbException ;

    public abstract void save(Object entity) throws DbException ;

    public abstract void saveAll(List<?> entities) throws DbException;

    public abstract boolean saveBindingId(Object entity) throws DbException ;

    public abstract void saveBindingIdAll(List<?> entities) throws DbException ;

    public abstract void deleteById(Class<?> entityType, Object idValue) throws DbException ;

    public abstract void delete(Object entity) throws DbException ;

    public abstract void delete(Class<?> entityType, WhereBuilder whereBuilder) throws DbException ;

    public abstract void deleteAll(List<?> entities) throws DbException ;

    public abstract void deleteAll(Class<?> entityType) throws DbException;

    public abstract void update(Object entity, String... updateColumnNames) throws DbException ;

    public abstract void update(Object entity, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException ;

    public abstract void updateAll(List<?> entities, String... updateColumnNames) throws DbException ;

    public abstract void updateAll(List<?> entities, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException ;

    public abstract <T> T findById(Class<T> entityType, Object idValue) throws DbException ;

    public abstract <T> T findFirst(Selector selector) throws DbException ;

    public abstract <T> T findFirst(Class<T> entityType) throws DbException ;

    public abstract <T> T findFirst(Class<T> entityType, WhereBuilder whereBuilder) throws DbException ;

    public abstract <T> T findFirst(Object entity) throws DbException ;

    public abstract <T> List<T> findAll(Selector selector) throws DbException ;

    public abstract <T> List<T> findAll(Class<T> entityType) throws DbException ;

    public abstract <T> List<T> findAll(Class<T> entityType, WhereBuilder whereBuilder) throws DbException ;

    public abstract <T> List<T> findAll(Object entity) throws DbException ;
    public abstract DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException ;

    public abstract DbModel findDbModelFirst(DbModelSelector selector) throws DbException ;

    public abstract List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException ;

    public abstract List<DbModel> findDbModelAll(DbModelSelector selector) throws DbException ;

    public abstract long count(Selector selector) throws DbException;
    public abstract long count(Class<?> entityType) throws DbException ;

    public abstract long count(Class<?> entityType, WhereBuilder whereBuilder) throws DbException ;

    public abstract long count(Object entity) throws DbException;
    
    public abstract void createTableIfNotExist(Class<?> entityType) throws DbException ;

    public abstract boolean tableIsExist(Class<?> entityType) throws DbException ;

    public abstract void dropDb() throws DbException ;

    public abstract void dropTable(Class<?> entityType) throws DbException ;
    
    public abstract void execNonQuery(SqlInfo sqlInfo) throws DbException;

    public abstract void execNonQuery(String sql) throws DbException ;
    
    protected DaoConfig daoConfig;
    public DaoConfig getDaoConfig() {
        return daoConfig;
    }
    
    public static class DaoConfig {
        private Context context;
        private String dbName = "xUtils.db"; // default db name
        private int dbVersion = 1;
        private DbUpgradeListener dbUpgradeListener;

        private String dbDir;

        public DaoConfig(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            if (!TextUtils.isEmpty(dbName)) {
                this.dbName = dbName;
            }
        }

        public int getDbVersion() {
            return dbVersion;
        }

        public void setDbVersion(int dbVersion) {
            this.dbVersion = dbVersion;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return dbUpgradeListener;
        }

        public void setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
        }

        public String getDbDir() {
            return dbDir;
        }

        /**
         * set database dir
         *
         * @param dbDir If dbDir is null or empty, use the app default db dir.
         */
        public void setDbDir(String dbDir) {
            this.dbDir = dbDir;
        }
    }
    
    public interface DbUpgradeListener {
        public void onUpgrade(DbUtils db, int oldVersion, int newVersion);
    }
    
}

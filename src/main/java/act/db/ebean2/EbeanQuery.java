package act.db.ebean2;

/*-
 * #%L
 * ACT AAA Plugin
 * %%
 * Copyright (C) 2015 - 2017 ActFramework
 * %%
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
 * #L%
 */

import act.db.Dao;
import io.ebean.*;
import org.osgl.$;
import org.osgl.util.C;
import org.osgl.util.E;
import org.osgl.util.Generics;
import org.osgl.util.S;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EbeanQuery<MODEL_TYPE> implements Query<MODEL_TYPE>, Dao.Query<MODEL_TYPE, EbeanQuery<MODEL_TYPE>> {

    protected Class<MODEL_TYPE> modelType;

    Query<MODEL_TYPE> q;
    EbeanDao dao;

    public EbeanQuery() {
        List<Type> typeParams = Generics.typeParamImplementations(getClass(), EbeanQuery.class);
        int sz = typeParams.size();
        if (sz > 1) {
            dao = $.cast(typeParams.get(1));
        }
        if (sz > 0) {
            modelType = $.cast(typeParams.get(0));
        }
    }

    public EbeanQuery(EbeanDao dao, Class<MODEL_TYPE> modelType) {
        this.modelType = modelType;
        EbeanServer ebean = dao.ebean();
        E.NPE(ebean);
        q = ebean.createQuery(modelType);
        this.dao = dao;
    }

    public Query<MODEL_TYPE> rawQuery() {
        return q;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> asOf(Timestamp timestamp) {
        q.asOf(timestamp);
        return this;
    }

    @Override
    public <A> A findSingleAttribute() {
        return q.findSingleAttribute();
    }

    @Override
    public List<Version<MODEL_TYPE>> findVersions() {
        return q.findVersions();
    }

    @Override
    public List<Version<MODEL_TYPE>> findVersionsBetween(Timestamp timestamp, Timestamp timestamp1) {
        return q.findVersionsBetween(timestamp, timestamp1);
    }

    @Override
    public EbeanQuery<MODEL_TYPE> apply(FetchPath fetchPath) {
        q.apply(fetchPath);
        return this;
    }

    @Override
    public ExpressionList<MODEL_TYPE> text() {
        return q.text();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setUseDocStore(boolean b) {
        q.setUseDocStore(b);
        return this;
    }

    @Override
    public Query<MODEL_TYPE> setBeanCacheMode(CacheMode beanCacheMode) {
        q.setBeanCacheMode(beanCacheMode);
        return this;
    }

    @Override
    public Query<MODEL_TYPE> setProfileLocation(ProfileLocation profileLocation) {
        q.setProfileLocation(profileLocation);
        return this;
    }

    @Override
    public int update() {
        return q.update();
    }

    @Override
    public int delete() {
        return q.delete();
    }

    @Override
    public Object getId() {
        return q.getId();
    }

    @Override
    public Class<MODEL_TYPE> getBeanType() {
        return q.getBeanType();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setDisableLazyLoading(boolean b) {
        q.setDisableLazyLoading(b);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> offset(int pos) {
        q.setFirstRow(pos);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> limit(int limit) {
        q.setMaxRows(limit);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> orderBy(String... fieldList) {
        E.illegalArgumentIf(fieldList.length == 0);
        q.order(S.join(" ", fieldList));
        return this;
    }

    @Override
    public MODEL_TYPE first() {
        return q.findOne();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetchQuery(String path, String fetchProperties) {
        q.fetchQuery(path, fetchProperties);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetchLazy(String path, String fetchProperties) {
        q.fetchLazy(path, fetchProperties);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetchQuery(String path) {
        q.fetchQuery(path);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetchLazy(String path) {
        q.fetchLazy(path);
        return this;
    }

    @Override
    public Iterable<MODEL_TYPE> fetch() {
        C.List<MODEL_TYPE> list = C.newList();
        QueryIterator<MODEL_TYPE> qi = findIterate();
        while (qi.hasNext()) {
            list.add(qi.next());
        }
        qi.close();
        return list;
// we need to close the query iterable right now otherwise it hold the data connection forever
//        return new Iterable<MODEL_TYPE>() {
//            @Override
//            public Iterator<MODEL_TYPE> iterator() {
//                return findIterate();
//            }
//        };
    }

    @Override
    public long count() {
        return q.findCount();
    }

    // --- Ebean Query methods: delegate to q


    @Override
    public EbeanQuery<MODEL_TYPE> setIncludeSoftDeletes() {
        q.setIncludeSoftDeletes();
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> asDraft() {
        q.asDraft();
        return this;
    }

    @Override
    public boolean isAutoTuned() {
        return q.isAutoTuned();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setAutoTune(boolean b) {
        q.setAutoTune(b);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setDisableReadAuditing() {
        q.setDisableReadAuditing();
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setUseQueryCache(CacheMode useQueryCache) {
        q.setUseQueryCache(useQueryCache);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setProfileId(int i) {
        q.setProfileId(i);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setRawSql(RawSql rawSql) {
        q.setRawSql(rawSql);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setDocIndexName(String indexName) {
        q.setDocIndexName(indexName);
        return this;
    }

    @Override
    public void findEach(Consumer<MODEL_TYPE> consumer) {
        q.findEach(consumer);
    }

    @Override
    public void findEachWhile(Predicate<MODEL_TYPE> consumer) {
        q.findEachWhile(consumer);
    }

    @Override
    public EbeanQuery<MODEL_TYPE> forUpdate() {
        q.forUpdate();
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> forUpdateNoWait() {
        q.forUpdateNoWait();
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> forUpdateSkipLocked() {
        q.forUpdateSkipLocked();
        return this;
    }

    @Override
    public ForUpdate getForUpdateMode() {
        return q.getForUpdateMode();
    }

    @Override
    public void cancel() {
        q.cancel();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> copy() {
        q.copy();
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setPersistenceContextScope(PersistenceContextScope scope) {
        q.setPersistenceContextScope(scope);
        return this;
    }

    @Override
    public ExpressionFactory getExpressionFactory() {
        return q.getExpressionFactory();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setLazyLoadBatchSize(int lazyLoadBatchSize) {
        q.setLazyLoadBatchSize(lazyLoadBatchSize);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> select(String fetchProperties) {
        q.select(fetchProperties);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetch(String path, String fetchProperties) {
        q.fetch(path, fetchProperties);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetch(String assocProperty, String fetchProperties, FetchConfig fetchConfig) {
        q.fetch(assocProperty, fetchProperties, fetchConfig);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetch(String path) {
        q.fetch(path);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> fetch(String path, FetchConfig joinConfig) {
        q.fetch(path, joinConfig);
        return this;
    }

    @Override
    public List<Object> findIds() {
        return q.findIds();
    }

    @Override
    public QueryIterator<MODEL_TYPE> findIterate() {
        QueryIterator<MODEL_TYPE> i = q.findIterate();
        dao.registerQueryIterator(i);
        return i;
    }

    public void consume($.Visitor<MODEL_TYPE> visitor) {
        QueryIterator<MODEL_TYPE> i = q.findIterate();
        try {
            while (i.hasNext()) {
                MODEL_TYPE entity = i.next();
                visitor.visit(entity);
            }
        } finally {
            i.close();
        }
    }

    @Override
    public List<MODEL_TYPE> findList() {
        return q.findList();
    }

    @Override
    public Set<MODEL_TYPE> findSet() {
        return q.findSet();
    }

    @Override
    public <K> Map<K, MODEL_TYPE> findMap() {
        return q.findMap();
    }

    @Override
    public <A> List<A> findSingleAttributeList() {
        return q.findSingleAttributeList();
    }

    @Override
    public int findCount() {
        return q.findCount();
    }

    @Override
    public MODEL_TYPE findOne() {
        return q.findOne();
    }

    @Override
    public Optional<MODEL_TYPE> findOneOrEmpty() {
        return q.findOneOrEmpty();
    }

    @Override
    public FutureRowCount<MODEL_TYPE> findFutureCount() {
        return q.findFutureCount();
    }

    @Override
    public FutureIds<MODEL_TYPE> findFutureIds() {
        return q.findFutureIds();
    }

    @Override
    public FutureList<MODEL_TYPE> findFutureList() {
        return q.findFutureList();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setParameter(String name, Object value) {
        q.setParameter(name, value);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setParameter(int position, Object value) {
        q.setParameter(position, value);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setId(Object id) {
        q.setId(id);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> where(Expression expression) {
        q.where(expression);
        return this;
    }

    @Override
    public ExpressionList<MODEL_TYPE> where() {
        return q.where();
    }

    @Override
    public ExpressionList<MODEL_TYPE> filterMany(String propertyName) {
        return q.filterMany(propertyName);
    }

    @Override
    public ExpressionList<MODEL_TYPE> having() {
        return q.having();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> having(Expression addExpressionToHaving) {
        q.having(addExpressionToHaving);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> orderBy(String orderByClause) {
        q.orderBy(orderByClause);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> order(String orderByClause) {
        q.order(orderByClause);
        return this;
    }

    @Override
    public OrderBy<MODEL_TYPE> order() {
        return q.order();
    }

    @Override
    public OrderBy<MODEL_TYPE> orderBy() {
        return q.orderBy();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setOrder(OrderBy<MODEL_TYPE> orderBy) {
        q.setOrder(orderBy);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setOrderBy(OrderBy<MODEL_TYPE> orderBy) {
        q.setOrderBy(orderBy);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setDistinct(boolean isDistinct) {
        q.setDistinct(isDistinct);
        return this;
    }

    @Override
    public int getFirstRow() {
        return q.getFirstRow();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setFirstRow(int firstRow) {
        q.setFirstRow(firstRow);
        return this;
    }

    @Override
    public int getMaxRows() {
        return q.getMaxRows();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setMaxRows(int maxRows) {
        q.setMaxRows(maxRows);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setMapKey(String mapKey) {
        q.setMapKey(mapKey);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setUseCache(boolean useBeanCache) {
        q.setUseCache(useBeanCache);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setUseQueryCache(boolean useQueryCache) {
        q.setUseQueryCache(useQueryCache);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setReadOnly(boolean readOnly) {
        q.setReadOnly(readOnly);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setLoadBeanCache(boolean loadBeanCache) {
        q.setLoadBeanCache(loadBeanCache);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setTimeout(int secs) {
        q.setTimeout(secs);
        return this;
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setBufferFetchSizeHint(int fetchSize) {
        q.setBufferFetchSizeHint(fetchSize);
        return this;
    }

    @Override
    public String getGeneratedSql() {
        return q.getGeneratedSql();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> setForUpdate(boolean forUpdate) {
        q.setForUpdate(forUpdate);
        return this;
    }

    @Override
    public boolean isForUpdate() {
        return q.isForUpdate();
    }

    @Override
    public EbeanQuery<MODEL_TYPE> alias(String alias) {
        q.alias(alias);
        return this;
    }

    @Override
    public PagedList<MODEL_TYPE> findPagedList() {
        return q.findPagedList();
    }

    @Override
    public Set<String> validate() {
        return q.validate();
    }
}

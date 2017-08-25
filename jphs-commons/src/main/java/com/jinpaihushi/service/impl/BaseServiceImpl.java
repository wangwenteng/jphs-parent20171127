package com.jinpaihushi.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import com.jinpaihushi.context.SpringHelper;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.model.BaseModel;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.service.ReadonlyService;
import com.jinpaihushi.utils.exception.ExceptionHandler;
import com.github.pagehelper.Page;

public abstract class BaseServiceImpl<T extends BaseModel> implements
		ReadonlyService<T>, BaseService<T>, ApplicationEventPublisher {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ExceptionHandler exceptionHandler;

	public BaseServiceImpl() {
		super();
		logger.info("init:" + this.getClass().getSimpleName());
	}

	protected abstract BaseDao<T> getDao();

	protected void defaultModel(T model) {
		if (model.getStatus() == null) {
			model.setStatus(0);
		}
		if (model.getType() == null) {
			model.setType(0);
		}
		if (model.getCreateTime() == null) {
			model.setCreateTime(new Date());
		}
		if (model.getUpdateTime() == null) {
			model.setUpdateTime(new Date());
		}
	}

	@Override
	public String insert(T model) {
		defaultModel(model);
		int result = getDao().insert(model);
		if(result != 1){
			throw new RuntimeException("数据插入失败");
		}
		return model.getId();
	}

	@Override
	public int count(T model) {
		return getDao().count(model);
	}

	@Override
	public Page<T> query(T model, RowBounds row) {
		return getDao().query(model, row);
	}

	@Override
	public List<T> list(T model) {
		return getDao().list(model);
	}

	@Override
	public T loadById(String id) {
		return getDao().loadById(id);
	}

	@Override
	public boolean update(T model) {
		model.setUpdateTime(new Date());
		return getDao().update(model) > 0;
	}

	@Override
	public boolean deleteById(String id) {
		return getDao().deleteById(id) > 0;
	}

	@Override
	public boolean disableById(String id) {
		return getDao().disableById(id) > 0;
	}

	@Override
	public void publishEvent(ApplicationEvent event) {
		SpringHelper.context.publishEvent(event);
	}

	@Override
	public void publishEvent(Object event) {
		SpringHelper.context.publishEvent(event);
	}

	@Override
	public Page<T> query(T model) {
		return getDao().query(model);
	}

	@Override
	public T load(T model) {
		return getDao().load(model);
	}

	@Override
	public int inserts(List<T> models) {
		if (CollectionUtils.isEmpty(models))
			return 0;
		for (T model : models) {
			defaultModel(model);
		}
		return getDao().inserts(models);
	}

	@Override
	public boolean save(T model) {
		if (model.getId() == null) {
			this.insert(model);
			return true;
		} else {
			return this.update(model);
		}
	}

}

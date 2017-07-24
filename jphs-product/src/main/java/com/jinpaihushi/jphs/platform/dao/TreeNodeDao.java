package com.jinpaihushi.jphs.platform.dao;

import java.util.List;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.platform.model.TreeNode;

public interface TreeNodeDao extends BaseDao<TreeNode>{
	List<TreeNode> getProduct();
	List<TreeNode> getGoods();
}

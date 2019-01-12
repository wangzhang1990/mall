package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class ItemParamTest {
	@Autowired
	private TbItemParamMapper mapper;
	
	@Test
	public void demo1() {
		TbItemParam param = mapper.selectByPrimaryKey(25l);
		System.out.println(param);
		
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = mapper.selectByExample(example);
	}
}

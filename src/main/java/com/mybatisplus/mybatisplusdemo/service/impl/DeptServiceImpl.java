package com.mybatisplus.mybatisplusdemo.service.impl;

import com.mybatisplus.mybatisplusdemo.pojo.Dept;
import com.mybatisplus.mybatisplusdemo.mapper.DeptMapper;
import com.mybatisplus.mybatisplusdemo.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HQ
 * @since 2019-07-10
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}

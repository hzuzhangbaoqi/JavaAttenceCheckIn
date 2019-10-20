package ${basePackageServiceImpl};

import ${basePackageDao}.${modelNameUpperCamel}Mapper;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageModel}.${modelNameUpperCamel}Example;
import ${basePackageService}.${modelNameUpperCamel}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: ${modelNameUpperCamel}Service接口实现类
* @author ${author}
* @date ${date}
*/
@Service
public class ${modelNameUpperCamel}ServiceImpl extends AbstractBaseServiceImpl<${modelNameUpperCamel},Integer> implements ${modelNameUpperCamel}Service {

    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;
    /*
    @Autowired
    public void setBaseDAO(${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper) {
        super.setBaseDAO(${modelNameLowerCamel}Mapper);
    }*/

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public ${modelNameUpperCamel} get(Integer id) throws DataAccessException {
        return ${modelNameLowerCamel}Mapper.selectByPrimaryKey(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<${modelNameUpperCamel}> getAllList() throws DataAccessException {
        ${modelNameUpperCamel}Example example = new ${modelNameUpperCamel}Example();
        return ${modelNameLowerCamel}Mapper.selectByExample(example);
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        ${modelNameUpperCamel}Example example = new ${modelNameUpperCamel}Example();
        return ${modelNameLowerCamel}Mapper.countByExample(example);
    }

    /**
     * 新增Entity
     * @param entity
     */
    public ${modelNameUpperCamel} save(${modelNameUpperCamel} entity) throws DataAccessException {
        ${modelNameLowerCamel}Mapper.insertSelective(entity);
        return entity;
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(${modelNameUpperCamel} entity) throws DataAccessException {
        ${modelNameUpperCamel}Example example = new ${modelNameUpperCamel}Example();
        ${modelNameUpperCamel}Example.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(entity.getId());
        return ${modelNameLowerCamel}Mapper.updateByExample(entity, example);
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public ${modelNameUpperCamel} saveOrUpdate(${modelNameUpperCamel} entity) throws DataAccessException {
        if(existById(entity.getId())){
            update(entity);
            return entity;
        }else{
            return save(entity);
        }
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(Integer id) throws DataAccessException {
        ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(Integer[] ids) throws DataAccessException {
        ${modelNameUpperCamel}Example example = new ${modelNameUpperCamel}Example();
        ${modelNameUpperCamel}Example.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        ${modelNameLowerCamel}Mapper.deleteByExample(example);
    }

    @Override
    public boolean existById(Integer id) throws DataAccessException {
        ${modelNameUpperCamel}Example example = new ${modelNameUpperCamel}Example();
        ${modelNameUpperCamel}Example.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return ${modelNameLowerCamel}Mapper.countByExample(example)>0?true:false;
    }


    public int del(Integer id) throws DataAccessException {
        return ${modelNameLowerCamel}Mapper.deleteByPrimaryKey(id);
    }
    public int delByIds(Integer[] ids) throws DataAccessException {
        ${modelNameUpperCamel}Example example = new ${modelNameUpperCamel}Example();
        ${modelNameUpperCamel}Example.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return ${modelNameLowerCamel}Mapper.deleteByExample(example);
    }
}
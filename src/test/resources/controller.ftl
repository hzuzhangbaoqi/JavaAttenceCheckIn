
package ${basePackageController};

import ${basePackage}.common.JsonResult;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: ${modelNameUpperCamel}Controller类
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${baseRequestMapping}")
@Api(value = "${baseRequestMapping} 在线接口文档")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping("/insert")
    @ApiOperation(value = "insert", notes = "增加一条数据")
    public JsonResult<Integer> insert(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
      /*${modelNameLowerCamel}.setId(ApplicationUtils.getUUID());*/
       ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return new JsonResult<Integer>(1);
    }
    @RequestMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "根据id删除数据")
    public JsonResult<Integer> deleteById(@RequestParam Integer id) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.del(id);
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/update")
    @ApiOperation(value = "update", notes = "更新数据")
    public JsonResult<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return new JsonResult<Integer>(state);
    }

    @RequestMapping("/selectById")
    @ApiOperation(value = "selectById", notes = "更加id查询")
    public JsonResult<${modelNameUpperCamel}> selectById(@RequestParam Integer id) throws Exception {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.get(id);
        return new JsonResult<${modelNameUpperCamel}>(${modelNameLowerCamel});
    }

    /**
     * @Description: 分页查询
     * @param page 页码
     * @param size 每页条数
     * @Reutrn JsonResult<PageInfo<${modelNameUpperCamel}>>
     */
    @RequestMapping("/list")
    @ApiOperation(value = "list", notes = "批量查询")
    public JsonResult<PageInfo<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "0") Integer page,
               @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.getAllList();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return new JsonResult<PageInfo<${modelNameUpperCamel}>>(pageInfo);
    }
}
package com.innovate.modules.match.controller;

import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.modules.match.entity.MatchEventEntity;
import com.innovate.modules.match.service.MatchEventService;
import com.innovate.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/11/6
 **/
@RestController
@RequestMapping("innovate/match/event")
public class MatchEventController extends AbstractController {

    @Autowired
    private MatchEventService matchEventService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("innovate:event:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = matchEventService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有列表
     */
    @GetMapping("/all")
    public R all() {
        List<MatchEventEntity> matchEventEntityList = matchEventService.queryAllEvent();
        return R.ok().put("matchEventEntityList", matchEventEntityList);
    }

    /**
     * 统计各赛事信息
     */
    @PostMapping("/total")
    @RequiresPermissions("innovate:total:save")
    public R total(@RequestParam Map<String, Object> params) {

        if ("{}".equals(params) && params == null) {
            List<Long> eventIds = new ArrayList<>();
            List<MatchEventEntity> matchEventEntityList = matchEventService.queryAllEvent();
            for (MatchEventEntity matchEventEntity : matchEventEntityList) {
                eventIds.add(matchEventEntity.getEventId());
            }
            //封装params
            for (Long eventId : eventIds) {
                params.put("eventId", eventId);
                //先统计
                matchEventService.total(params);
                params = new HashMap<>();
            }
        }else{
            //先统计
            matchEventService.total(params);
        }
        //后遍历
        PageUtils page = matchEventService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{eventId}")
    @RequiresPermissions("innovate:event:info")
    public R info(@PathVariable("eventId") Integer eventId) {
        MatchEventEntity matchEventEntity = matchEventService.selectById(eventId);

        return R.ok().put("matchEventEntity", matchEventEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("innovate:event:save")
    public R save(@RequestBody MatchEventEntity matchEventEntity) {
        matchEventService.insert(matchEventEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("innovate:event:update")
    public R update(@RequestBody MatchEventEntity matchEventEntity) {
        matchEventService.updateAllColumnById(matchEventEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("innovate:event:delete")
    public R delete(@RequestBody Long[] eventIds) {

        matchEventService.deleteBatchIds(Arrays.asList(eventIds));

        return R.ok();
    }
}

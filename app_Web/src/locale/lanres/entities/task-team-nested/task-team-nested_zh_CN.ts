import TaskTeamNested_zh_CN_Base from './task-team-nested_zh_CN_base';

function getLocaleResource(){
    const TaskTeamNested_zh_CN_OwnData = {};
    const targetData = Object.assign(TaskTeamNested_zh_CN_Base(), TaskTeamNested_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;
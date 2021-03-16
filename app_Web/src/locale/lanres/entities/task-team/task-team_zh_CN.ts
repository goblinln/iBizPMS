import TaskTeam_zh_CN_Base from './task-team_zh_CN_base';

function getLocaleResource(){
    const TaskTeam_zh_CN_OwnData = {};
    const targetData = Object.assign(TaskTeam_zh_CN_Base(), TaskTeam_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;
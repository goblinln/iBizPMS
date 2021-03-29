import TaskTeam_en_US_Base from './task-team_en_US_base';

function getLocaleResource(){
    const TaskTeam_en_US_OwnData = {};
    const targetData = Object.assign(TaskTeam_en_US_Base(), TaskTeam_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
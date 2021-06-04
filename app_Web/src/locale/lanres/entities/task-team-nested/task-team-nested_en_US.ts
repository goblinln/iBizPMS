import TaskTeamNested_en_US_Base from './task-team-nested_en_US_base';

function getLocaleResource(){
    const TaskTeamNested_en_US_OwnData = {};
    const targetData = Object.assign(TaskTeamNested_en_US_Base(), TaskTeamNested_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
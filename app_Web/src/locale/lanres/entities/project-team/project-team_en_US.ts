import ProjectTeam_en_US_Base from './project-team_en_US_base';

function getLocaleResource(){
    const ProjectTeam_en_US_OwnData = {};
    const targetData = Object.assign(ProjectTeam_en_US_Base(), ProjectTeam_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
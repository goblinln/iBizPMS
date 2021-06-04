import ProjectMonthly_en_US_Base from './project-monthly_en_US_base';

function getLocaleResource(){
    const ProjectMonthly_en_US_OwnData = {};
    const targetData = Object.assign(ProjectMonthly_en_US_Base(), ProjectMonthly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
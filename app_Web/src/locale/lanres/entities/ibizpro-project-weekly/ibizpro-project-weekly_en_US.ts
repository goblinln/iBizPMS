import IbizproProjectWeekly_en_US_Base from './ibizpro-project-weekly_en_US_base';

function getLocaleResource(){
    const IbizproProjectWeekly_en_US_OwnData = {};
    const targetData = Object.assign(IbizproProjectWeekly_en_US_Base(), IbizproProjectWeekly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
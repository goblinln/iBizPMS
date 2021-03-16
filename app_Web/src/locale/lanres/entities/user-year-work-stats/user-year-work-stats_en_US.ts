import UserYearWorkStats_en_US_Base from './user-year-work-stats_en_US_base';

function getLocaleResource(){
    const UserYearWorkStats_en_US_OwnData = {};
    const targetData = Object.assign(UserYearWorkStats_en_US_Base(), UserYearWorkStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
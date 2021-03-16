import UserYearWorkStats_zh_CN_Base from './user-year-work-stats_zh_CN_base';

function getLocaleResource(){
    const UserYearWorkStats_zh_CN_OwnData = {};
    const targetData = Object.assign(UserYearWorkStats_zh_CN_Base(), UserYearWorkStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;
import IbizproProjectMonthly_zh_CN_Base from './ibizpro-project-monthly_zh_CN_base';

function getLocaleResource(){
    const IbizproProjectMonthly_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproProjectMonthly_zh_CN_Base(), IbizproProjectMonthly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;
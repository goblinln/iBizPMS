import IbizproProjectMonthly_en_US_Base from './ibizpro-project-monthly_en_US_base';

function getLocaleResource(){
    const IbizproProjectMonthly_en_US_OwnData = {};
    const targetData = Object.assign(IbizproProjectMonthly_en_US_Base(), IbizproProjectMonthly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
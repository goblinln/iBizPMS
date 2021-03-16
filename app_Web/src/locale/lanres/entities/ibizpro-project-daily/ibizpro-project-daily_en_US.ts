import IbizproProjectDaily_en_US_Base from './ibizpro-project-daily_en_US_base';

function getLocaleResource(){
    const IbizproProjectDaily_en_US_OwnData = {};
    const targetData = Object.assign(IbizproProjectDaily_en_US_Base(), IbizproProjectDaily_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
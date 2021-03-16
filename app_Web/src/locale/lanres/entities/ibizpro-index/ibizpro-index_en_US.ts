import IbizproIndex_en_US_Base from './ibizpro-index_en_US_base';

function getLocaleResource(){
    const IbizproIndex_en_US_OwnData = {};
    const targetData = Object.assign(IbizproIndex_en_US_Base(), IbizproIndex_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
import Build_en_US_Base from './build_en_US_base';

function getLocaleResource(){
    const Build_en_US_OwnData = {};
    const targetData = Object.assign(Build_en_US_Base(), Build_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
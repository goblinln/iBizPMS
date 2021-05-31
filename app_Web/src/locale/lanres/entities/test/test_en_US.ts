import Test_en_US_Base from './test_en_US_base';

function getLocaleResource(){
    const Test_en_US_OwnData = {};
    const targetData = Object.assign(Test_en_US_Base(), Test_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
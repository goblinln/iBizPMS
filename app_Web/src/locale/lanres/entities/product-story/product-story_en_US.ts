import ProductStory_en_US_Base from './product-story_en_US_base';

function getLocaleResource(){
    const ProductStory_en_US_OwnData = {};
    const targetData = Object.assign(ProductStory_en_US_Base(), ProductStory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
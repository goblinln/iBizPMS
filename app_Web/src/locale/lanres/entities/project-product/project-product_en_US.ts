import ProjectProduct_en_US_Base from './project-product_en_US_base';

function getLocaleResource(){
    const ProjectProduct_en_US_OwnData = {};
    const targetData = Object.assign(ProjectProduct_en_US_Base(), ProjectProduct_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;
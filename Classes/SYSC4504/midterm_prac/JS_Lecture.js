function myApplication1(){
    // Prompt the user to input age in years, weight in pounds and height in inches.
    let iAgeYears = 18;
    let iWeightPounds = 180;
    let iHeightInches = 70;
    let iGender = "Male";

    /*
    let array= [18, 180, 70, "Female"];
    let [iAgeYears, iWeightPounds, iHeightInches, iGender] = array;
    */

    // calculate the BMR value
    function BMR(iWeight, iHeight, iAge, iGender){
        let cBMR = 0;
        if(iGender == "Male"){
        cBMR = 66.0 + (6.3 * iWeight) + (12.9 * iHeight) - (6.8 * iAge);
        }else if (iGender == "Female"){
        cBMR = 655.0 + (4.3 * iWeight) + (4.7 * iHeight) - (4.7 * iAge);
        }
        return cBMR;
    };
    return BMR(iWeightPounds, iHeightInches, iAgeYears, iGender);
}

// https://pythontutor.com/visualize.html#code=//%20Prompt%20the%20user%20to%20input%20age%20in%20years,%20weight%20in%20pounds%20and%20height%20in%20inches.%0Alet%20iAgeYears%20%3D%2018%3B%0Alet%20iWeightPounds%20%3D%20180%3B%0Alet%20iHeightInches%20%3D%2070%3B%0Alet%20iGender%20%3D%20%22Male%22%3B%0A%0A//%20calculate%20the%20BMR%20value%0Afunction%20BMR%28iWeight,%20iHeight,%20iAge,%20iGender%29%7B%0A%20%20%20%20let%20cBMR%20%3D%200%3B%0A%20%20%20%20if%28iGender%20%3D%3D%20%22Male%22%29%7B%0A%20%20%20%20%20%20cBMR%20%3D%2066.0%20%2B%20%286.3%20*%20iWeight%29%20%2B%20%2812.9%20*%20iHeight%29%20-%20%286.8%20*%20iAge%29%3B%0A%20%20%20%20%7Delse%20if%20%28iGender%20%3D%3D%20%22Female%22%29%7B%0A%20%20%20%20%20%20cBMR%20%3D%20655.0%20%2B%20%284.3%20*%20iWeight%29%20%2B%20%284.7%20*%20iHeight%29%20-%20%284.7%20*%20iAge%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20return%20cBMR%3B%0A%7D%3B%0Aconsole.log%28BMR%28iWeightPounds,%20iHeightInches,%20iAgeYears,%20iGender%29%29%3B%0A%0Alet%20array%3D%20%5B18,%20180,%2070,%20%22Female%22%5D%3B%0A%5BiAgeYears,%20iWeightPounds,%20iHeightInches,%20iGender%5D%20%3D%20array%3B%0Aconsole.log%28BMR%28iWeightPounds,%20iHeightInches,%20iAgeYears,%20iGender%29%29%3B%0A&cumulative=false&heapPrimitives=true&mode=edit&origin=opt-frontend.js&py=js&rawInputLstJSON=%5B%5D&textReferences=false

function myApplication2(){
    // Prompt the user to input age in years, weight in pounds and height in inches.
    let iAgeYears = prompt("Enter age (years)");
    let iWeightPounds = prompt("Enter weight (lbs)");
    let iHeightInches = prompt("Enter height (inches)");
    let iGender = prompt("Enter the gender (Male/Female)");

    // calculate the BMR value
    function BMR(iWeight, iHeight, iAge, iGender){
        let cBMR = 0;
        if(iGender == "Male"){
            cBMR = 66.0 + (6.3 * iWeight) + (12.9 * iHeight) - (6.8 * iAge);
        }else if (iGender == "Female"){
            cBMR = 655.0 + (4.3 * iWeight) + (4.7 * iHeight) - (4.7 * iAge);
        }
        return cBMR;
    };

    return BMR(iWeightPounds, iHeightInches, iAgeYears, iGender);
}

function myApplication3(){
    // Prompt the user to input age in years, weight in pounds and height in inches.
    let inputArray = prompt("Enter your age (years), weight (lbs), height (inches), gender (Male/Female)").split(", ");
    //document.write(inputArray[0], inputArray[1], inputArray[2], inputArray[3]);
    // calculate the BMR value

    function BMR(iWeight, iHeight, iAge, iGender){
        let cBMR = 0;
        if(iGender == "Male"){
            cBMR = 66.0 + (6.3 * iWeight) + (12.9 * iHeight) - (6.8 * iAge);
        }else if (iGender == "Female"){
            cBMR = 655.0 + (4.3 * iWeight) + (4.7 * iHeight) - (4.7 * iAge);
        }
        return cBMR;
    };

    return BMR(inputArray[0], inputArray[1], inputArray[2], inputArray[3]);
}

function avtivityLevel(level){
    // Sedentary (little or no exercise): 1.2 
    let factor = 0;
    switch (level){
        case "Sedentary":   // Sedentary (little or no exercise): 1.2 
            factor = 1.2;
            break;
        case "Light":       // Lightly active (exercise 1-3 days/week): 1.375 
            factor = 1.375;
            break;
        case "Moderately":  // Moderately active (exercise 3-5 days/week): 1.55 
            factor = 1.55;
            break;
        case "Very":        // Very active (exercise 6-7 days a week): 1.725 
            factor = 1.755;
            break;
        case "Extra":       // Extra active (exercise & physical job or 2x training): 1.9
            factor = 1.9;
            break;
        default:
            factor = 0;
    }
    return factor;
}
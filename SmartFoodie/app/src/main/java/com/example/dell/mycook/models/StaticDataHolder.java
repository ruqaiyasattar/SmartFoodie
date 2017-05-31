package com.example.dell.mycook.models;

import java.util.ArrayList;

/**
 * Created by dell on 3/16/2017.
 */

public class StaticDataHolder {
    public static ArrayList<YoutubeVideoListItem[]> videos = new ArrayList<>();

    public static void loadData() {
        //choclate video dalni hai
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Choclate Moose cake", "https://www.youtube.com/watch?v=_wBqJEySfaA"),
                        new YoutubeVideoListItem("Choclate cake Recipe", "https://www.youtube.com/watch?v=QKdJyaecCBo"),
                        new YoutubeVideoListItem("HomeMade Choclate Donut", "https://www.youtube.com/watch?v=QkFZ4TvWVI0"),
                        new YoutubeVideoListItem("Molten Choclate Lava cake ", "https://www.youtube.com/watch?v=jWaf5lgDEOM"),
                        new YoutubeVideoListItem("Hot Choclate", "https://www.youtube.com/watch?v=T4dPdXHIIlo"),
                        new YoutubeVideoListItem("Coffaholic Desert", "https://www.youtube.com/watch?v=aW_A86lYaq8"),
                        new YoutubeVideoListItem("Choclate pudding pie", "https://www.youtube.com/watch?v=sw4VX3XcfNA"),
                        new YoutubeVideoListItem("fudgy brownie Recipe", "https://www.youtube.com/watch?v=_CX4pQ5-ick"),
                        new YoutubeVideoListItem("Choclate milk shake", "https://www.youtube.com/watch?v=4mX-tz5kbrk"),

                }
        );
        //fish video
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Fry Fish with masala", "https://www.youtube.com/watch?v=H_cXmLuccIs"),
                        new YoutubeVideoListItem("Crispy Fried fish", "https://www.youtube.com/watch?v=q6HBsWN9Jus"),
                        new YoutubeVideoListItem("Finger fish", "https://www.youtube.com/watch?v=21wQLz9zytg"),
                        new YoutubeVideoListItem("Fish Curry", "https://www.youtube.com/watch?v=8ocFDQqLWyE"),
                        new YoutubeVideoListItem("Finger Fish with tzatziki sauce ", "https://www.youtube.com/watch?v=21wQLz9zytg"),
                        new YoutubeVideoListItem("Finger Biryani", "https://www.youtube.com/watch?v=igD00IUAluQ"),
                        
                        
                }
        );
        //chicken video
        videos.add(
                new YoutubeVideoListItem[]{
                      new YoutubeVideoListItem("Chicken Chapli Kabab", "https://www.youtube.com/watch?v=Z7mcYrqXtWQ"),
                        new YoutubeVideoListItem("Chicken Potato Kabab", "https://www.youtube.com/watch?v=RG9-GVlH6qw"),
                        new YoutubeVideoListItem("Hyderabadi Dum Chicken Recipe", "https://www.youtube.com/watch?v=NaP2S-A_230"),
                        new YoutubeVideoListItem("Chicken Tikka", "https://www.youtube.com/watch?v=8HT9Gc8vtQE"),
                        new YoutubeVideoListItem("Chicken Chapli Kabab", "https://www.youtube.com/watch?v=Z7mcYrqXtWQ"),
                        new YoutubeVideoListItem("Chicken Potato Kabab", "https://www.youtube.com/watch?v=RG9-GVlH6qw"),
                }
        );
        //mix vege
        videos.add(
                new YoutubeVideoListItem[]{
                       new YoutubeVideoListItem("Sweet and Sour Vegetables", "https://www.youtube.com/watch?v=LLZGv_QCpNc"),
                        new YoutubeVideoListItem("Sarson ka Saag", "https://www.youtube.com/watch?v=XH2_pWrguvI"),
                        new YoutubeVideoListItem("Mix Vegetable", "https://www.youtube.com/watch?v=mh1G7oOm-p4"),
                        new YoutubeVideoListItem("Mix Vegetable Korma", "https://www.youtube.com/watch?v=yJEsS0kt45c"),
                        new YoutubeVideoListItem("Sweet and Sour Vegetables", "https://www.youtube.com/watch?v=LLZGv_QCpNc"),
                        new YoutubeVideoListItem("Sarson ka Saag", "https://www.youtube.com/watch?v=XH2_pWrguvI"),
                }
        );
        //pizza
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Bread Pizza", "https://www.youtube.com/watch?v=popXblRHafY"),
                        new YoutubeVideoListItem("Pizza Sauce", "https://www.youtube.com/watch?v=su3LV6UIewM"),
                        new YoutubeVideoListItem("Pizza Without Oven", "https://www.youtube.com/watch?v=yohIMERveHI"),
                        new YoutubeVideoListItem("Stuffed Crust Pizza & Pizza Dough", "https://www.youtube.com/watch?v=DRjptS4-VD8&spfreload=10"),
                        new YoutubeVideoListItem("Bread Pizza", "https://www.youtube.com/watch?v=popXblRHafY"),
                        new YoutubeVideoListItem("Pizza Sauce", "https://www.youtube.com/watch?v=su3LV6UIewM"),
                }
        );
//zinger vidoes
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Zinger Burger", "https://www.youtube.com/watch?v=GARlHqNLj_w"),
                        new YoutubeVideoListItem("Zinger Burger", "https://www.youtube.com/watch?v=N8zlkfWQgK0"),
                        new YoutubeVideoListItem("Crispy Chicken Burger", "https://www.youtube.com/watch?v=hoM7Rs5Hrac"),
                        new YoutubeVideoListItem("Crispy Chicken", "https://www.youtube.com/watch?v=jxau346dUiw"),
                        new YoutubeVideoListItem("Zinger Burger", "https://www.youtube.com/watch?v=GARlHqNLj_w"),
                        new YoutubeVideoListItem("Zinger Burger", "https://www.youtube.com/watch?v=N8zlkfWQgK0"),
                }
        );
//break fast
        videos.add(
                new YoutubeVideoListItem[]{
     new YoutubeVideoListItem("Omelette Roll with chicken and cheese", "https://www.youtube.com/watchv=DYnM9_1XWPY&list=PLqbUw5kmg6AENxd_0ZGq59P572iDYfZBA&index=3"),
      new YoutubeVideoListItem("Potato Omelette With Cheese", "https://www.youtube.com/watch?v=WmEkEzNJunI&list=PLqbUw5kmg6AENxd_0ZGq59P572iDYfZBA&index=4"),
                        new YoutubeVideoListItem("Fried Egg Salsa", "https://www.youtube.com/watch?v=_EYpunjvs0k&list=PLqbUw5kmg6AENxd_0ZGq59P572iDYfZBA&index=2"),
     new YoutubeVideoListItem("Omelette Roll with chicken and cheese", "https://www.youtube.com/watchv=DYnM9_1XWPY&list=PLqbUw5kmg6AENxd_0ZGq59P572iDYfZBA&index=3"),
      new YoutubeVideoListItem("Potato Omelette With Cheese", "https://www.youtube.com/watch?v=WmEkEzNJunI&list=PLqbUw5kmg6AENxd_0ZGq59P572iDYfZBA&index=4"),
                        new YoutubeVideoListItem("Baked Egg", "https://www.youtube.com/watch?v=9tBTUuNdOig&index=5&list=PLqbUw5kmg6AENxd_0ZGq59P572iDYfZBA"),
                }
        );
//lunch
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Chicken Malai Boti", "https://www.youtube.com/watch?v=DL0w2dmBgTc"),
                        new YoutubeVideoListItem("Croquette Balls", "https://www.youtube.com/watch?v=MXuVkUZaagE"),
                        new YoutubeVideoListItem("Fried Potato (cheese) Stuffed Bread Balls ", "https://www.youtube.com/watch?v=mTaJQ2-G9qc"),
                        new YoutubeVideoListItem("Bread Cheese Cigars", "https://www.youtube.com/watch?v=2UN_sK2vj2A"),
                        new YoutubeVideoListItem("Spring Rolls ", "https://www.youtube.com/watch?v=34LxJ9XwKts"),
                        new YoutubeVideoListItem("Nargisi Kababs", "https://www.youtube.com/watch?v=vO71e73HWl4"),}
        );
//dinner
        videos.add(
                new YoutubeVideoListItem[]{
                       new YoutubeVideoListItem("Mutton Chops", "https://www.youtube.com/watch?v=Ps32iR7Zw1M&list=PLqbUw5kmg6AE7mooU4WfFKcMJK7gxyxjz"),
                        new YoutubeVideoListItem(" Beef Nihari", "https://www.youtube.com/watch?v=mZOXDAvMCM4&list=PLqbUw5kmg6AE7mooU4WfFKcMJK7gxyxjz&index=2"),
                        new YoutubeVideoListItem("Beef Keema Briyan ", "https://www.youtube.com/watch?v=dyn15W4Wi_M&list=PLqbUw5kmg6AE7mooU4WfFKcMJK7gxyxjz&index=5"),
                        new YoutubeVideoListItem("Namkeen Gosht ", "https://www.youtube.com/watch?v=sHioucZUUOM&index=7&list=PLqbUw5kmg6AE7mooU4WfFKcMJK7gxyxjz"),
               new YoutubeVideoListItem("Chicken Alfredo Fettuccine", "https://www.youtube.com/watch?v=dJEjnk-mW0s&list=PLqbUw5kmg6AE7mooU4WfFKcMJK7gxyxjz&index=8"),
                   new YoutubeVideoListItem(" BBQ Chicken Wings", "https://www.youtube.com/watch?v=GK6P3sZLAWM&index=10&list=PLqbUw5kmg6AE7mooU4WfFKcMJK7gxyxjz"),}
                       
                
        );
//cookies
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Chocolate Pudding Pie ", "https://www.youtube.com/watch?v=sw4VX3XcfNA"),
                        new YoutubeVideoListItem("Coffaholic Dessert", "https://www.youtube.com/watch?v=aW_A86lYaq8"),
                        new YoutubeVideoListItem("Chocolate Chip Cookies ", "https://www.youtube.com/watch?v=jjTvhhvOucQ"),
                        new YoutubeVideoListItem("Chocolate Donuts", "https://www.youtube.com/watch?v=QkFZ4TvWVI0"),
                        new YoutubeVideoListItem("Vanilla Ice Cream ", "https://www.youtube.com/watch?v=O30sDoTgqeQ"),
                        new YoutubeVideoListItem("Pista Badaam Kulfi", "https://www.youtube.com/watch?v=WLqwbM2Zfjo"),
                }
        );
//juices
        videos.add(
                new YoutubeVideoListItem[]{
                        new YoutubeVideoListItem("Falsa Juice ", "https://www.youtube.com/watch?v=J26-PGXSWHo"),
                        new YoutubeVideoListItem("Chocolate Milkshake & Ganache", "https://www.youtube.com/watch?v=4mX-tz5kbrk"),
                        new YoutubeVideoListItem("Peach Lemonade  ", "https://www.youtube.com/watch?v=VnfY78MifMs"),
                        new YoutubeVideoListItem("Watermelon Drink", "https://www.youtube.com/watch?v=8YxNwXQlzS4"),
                        new YoutubeVideoListItem("Mango Fusion Salad", "https://www.youtube.com/watch?v=pbBdl7I78us"),
                        new YoutubeVideoListItem("Ice cream Falooda", "https://www.youtube.com/watch?v=rGUD9buuK3M"),
                }
        );
//yogurt videos
        videos.add(
                new YoutubeVideoListItem[]{
                         new YoutubeVideoListItem("Baingan with Dahi Eggplant with Yogurt ", "https://www.youtube.com/watch?v=8Aa7zkIUip8"),
                        new YoutubeVideoListItem("Smokey Yogurt Kabab", "https://www.youtube.com/watch?v=fMYxIKeHvt8"),
                        new YoutubeVideoListItem("Lahori Dahi bhalla", "https://www.youtube.com/watch?v=IcLlZSScYSQ"),
                        new YoutubeVideoListItem("Dahi Chana Chaat", "https://www.youtube.com/watch?v=ZOc9fJJNcII"),
                        new YoutubeVideoListItem("Daal Dahi Baray", "https://www.youtube.com/watch?v=FncOw73_ywc"),
                         new YoutubeVideoListItem("Dahi Chana Chaat", "https://www.youtube.com/watch?v=ZOc9fJJNcII"),
                }
        );
//rice videos
        videos.add(
                new YoutubeVideoListItem[]{
                      new YoutubeVideoListItem("Fried Rice", "https://www.youtube.com/watch?v=HUcmodfD7A8"),
                        new YoutubeVideoListItem("Masala Fried Rice", "https://www.youtube.com/watch?v=scKS31hoV_I"),
                        new YoutubeVideoListItem("Matar Chawal", "https://www.youtube.com/watch?v=64Th71HNjgk"),
                        new YoutubeVideoListItem("Mughlai Pulao", "https://www.youtube.com/watch?v=wLucIuFv1Do"),
                        new YoutubeVideoListItem("Hara Masala Biryani", "https://www.youtube.com/watch?v=axj2bWtw4SU"),
                         new YoutubeVideoListItem(" Singaporean Rice", "https://www.youtube.com/watch?v=MyM2KB93L3A"),
                }
        );
//cooking tips
        videos.add(
                new YoutubeVideoListItem[]{
                         new YoutubeVideoListItem("Coleslaw", "https://www.youtube.com/watch?v=J1VKYHG4-BI"),
                        new YoutubeVideoListItem("Russian Salad", "https://www.youtube.com/watch?v=4Ro-cEm5EZE"),
                        new YoutubeVideoListItem("Fattoush Salad", "https://www.youtube.com/watch?v=EIHxNtAhjgk"),
                        new YoutubeVideoListItem("Fusion Salad", "https://www.youtube.com/watch?v=hKZCM2VHBh8"),
                        new YoutubeVideoListItem("Rice Kheer", "https://www.youtube.com/watch?v=k6i2t2OPO-8"),
                         new YoutubeVideoListItem("Rasmalai ", "https://www.youtube.com/watch?v=CyTN8fp8aTQ"),
                }
        );
//masala tips
        videos.add(
                new YoutubeVideoListItem[]{
                         new YoutubeVideoListItem("Masala Bhindi Okra", "https://www.youtube.com/watch?v=HC3wrenLSBM"),
                        new YoutubeVideoListItem("Masala Fried Rice", "https://www.youtube.com/watch?v=scKS31hoV_I"),
                        new YoutubeVideoListItem("Bombay Briyani with Homemade Masala", "https://www.youtube.com/watch?v=CDh1UuY8nmw"),
                        new YoutubeVideoListItem("Fry Kaleji Masala ", "https://www.youtube.com/watch?v=LRk2ypaCgPM"),
                        new YoutubeVideoListItem("Mutton Kunna Recipe with Kunnah masala", "https://www.youtube.com/watch?v=0ToTp63UYj0"),
                         new YoutubeVideoListItem("Spicy Fried Khichdi", "https://www.youtube.com/watch?v=H71MWbyhO0E"),
                }
        );
//foods for kids
        videos.add(
                new YoutubeVideoListItem[]{
                         new YoutubeVideoListItem("Chicken Nuggets", "https://www.youtube.com/watch?v=xCS7LAUsa1E"),
                        new YoutubeVideoListItem("Chocolate Chip Cookies", "https://www.youtube.com/watch?v=jjTvhhvOucQ"),
                        new YoutubeVideoListItem("Meethi Tikiyan ", "https://www.youtube.com/watch?v=wYVQPc9G3lg"),
                        new YoutubeVideoListItem("Chicken Potato kabab", "https://www.youtube.com/watch?v=RG9-GVlH6qw"),
                        new YoutubeVideoListItem("Spicy & Saucy Drumsticks", "https://www.youtube.com/watch?v=MKJ_tcvGGUI"),
                         new YoutubeVideoListItem("Chicken Fajita Pasta", "https://www.youtube.com/watch?v=E-rqG-2F9ks"),
                }
        );

    }
}

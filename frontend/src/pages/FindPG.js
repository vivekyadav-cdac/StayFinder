import React, { useState, useEffect } from 'react';
import './FindPG.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import PGCard from '../components/PGCard';
import axios from 'axios';
import FilterSidebar from '../components/FilterSidebar';
import { useNavigate } from 'react-router-dom';

const FindPG = () => {
  const navigate = useNavigate();
  const [pgs, setPgs] = useState([
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.532126,
          "lng": 73.82863259999999
        },
        "viewport": {
          "northeast": {
            "lat": 18.53347582989272,
            "lng": 73.83003957989271
          },
          "southwest": {
            "lat": 18.53077617010728,
            "lng": 73.82733992010726
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "Youthville SB Road - Premium Student Hostel & PG in Pune",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 4480,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/116775645665035108459\">Youthville SB Road - Premium Student Hostel &amp; PG in Pune</a>"
          ],
          "photo_reference": "ATKogpdil9EKJ9MvL_EKzVJ1jhxAEPnwrwk2vVkwMCzau-jVLVbmj-XywF63iS7pDo1KqnzbjJ8OEDyYvy6Su_P0nQ9XQknMgPqxJKt9L634R9_Nq3FI63zMbSpfPQm6tFXrtX1BuxUgq2-iJ3DZ7Oomro2CRhLxO20n93gt75UTFuy-67NrPnDSeeUC5xXX_Cz6IByY3moNYhmc-mtfFovbysOabASnGusDEqqZu0wXEgmwoJgqe_DyCFZ6w8j5WVfevI2-5wcUshF5HdRePlAMfi_coYfKrtznyImq0MKJMJHTkVucUwc",
          "width": 6720
        }
      ],
      "place_id": "ChIJZy77nyC_wjsR0EwjYi4wAYg",
      "plus_code": {
        "compound_code": "GRJH+VF Pune, Maharashtra",
        "global_code": "7JCMGRJH+VF"
      },
      "rating": 4.5,
      "reference": "ChIJZy77nyC_wjsR0EwjYi4wAYg",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 468,
      "vicinity": "Senapati Bapat Rd, Shivaji Co operative Housing Society, Bhageerath, Gokhalenagar, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5458961,
          "lng": 73.8041812
        },
        "viewport": {
          "northeast": {
            "lat": 18.54723782989272,
            "lng": 73.80566707989271
          },
          "southwest": {
            "lat": 18.54453817010728,
            "lng": 73.80296742010727
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "Zolo Kings Landing - PG in Baner",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 1200,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/118014864903978581096\">Zolo Kings Landing - PG in Baner</a>"
          ],
          "photo_reference": "ATKogpdnXH09NdCZi7A2ROdR3mttys9F1WrPEaxjBjVe31OL-nL1mrIq_jrS4B4rcPuVZowHtdDNw-3tauUwSWKJYevqOHn2dQnuP8irJ858JQ5P87R_9byimNmstE6a5tUkllSy1bSGAZD-EnwXtFgjxCgvvm79D7WS4IhJKAamiFFE3TSq-a0etb2jpOp1CL37v8jdmFNJBtVhJUTL0L6AzpDswq6ZEEfh88dANF8-bbgwknLBFwmPOvdmNLWFWY_8cBuEnFIL_iZYy_8c0bQvL1lDuj2WX6Hp-afH7Y-qh1ODtaBRkvQ",
          "width": 1600
        }
      ],
      "place_id": "ChIJVRq4URi_wjsReHM_-aK6T7s",
      "plus_code": {
        "compound_code": "GRW3+9M Pune, Maharashtra",
        "global_code": "7JCMGRW3+9M"
      },
      "rating": 4,
      "reference": "ChIJVRq4URi_wjsReHM_-aK6T7s",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 443,
      "vicinity": "Survey No. 17, Housai Complex, Someshwarwadi Rd, next to Rajwada Hotel, Ward No. 8, Baner, Pashan, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5058464,
          "lng": 73.8398931
        },
        "viewport": {
          "northeast": {
            "lat": 18.50720252989272,
            "lng": 73.84124287989272
          },
          "southwest": {
            "lat": 18.50450287010728,
            "lng": 73.83854322010727
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/lodging-71.png",
      "icon_background_color": "#909CE1",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/hotel_pinlet",
      "name": "Yash Shelters",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 3647,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/102492622581756673193\">Yash Shelters (PG Accommodation For Working Women &amp; Girls)</a>"
          ],
          "photo_reference": "ATKogpdaVgIp1vGGuxz20Gu7rhbRcbUnyKfq4W431JrHyfigyihkaswvssv6FjyqzM4mn9Bjja3LcLQgnoeejbvNuotRHCM3r2AkL-2xgOxCwl3ap7dDA4GeQIJEwIrFa8pn2pGAJRw9IO1CSStWh92TQU11HSWJAoW6C7lHAge50oiagvDI7pugWTQgILo5QBxTrxUaR2i94vhYaP-yXvhyof9w9ThsydUYgnt34Zj46DrnNsnKcDVZUag5qbRezV3n84RwuTXeePbqNd13uWheGac_H-0EJuiX5FExhP4t0F7MuHDbwkU",
          "width": 5470
        }
      ],
      "place_id": "ChIJZbAAcBW_wjsRevacjskdAvc",
      "plus_code": {
        "compound_code": "GR4Q+8X Pune, Maharashtra",
        "global_code": "7JCMGR4Q+8X"
      },
      "rating": 4.7,
      "reference": "ChIJZbAAcBW_wjsRevacjskdAvc",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 155,
      "vicinity": "Anand Baug, Co-op Housing Society, Plot No. 9, Satsang Society, Phatak Baug, Navi Peth, Sadashiv Peth, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.529518,
          "lng": 73.8306704
        },
        "viewport": {
          "northeast": {
            "lat": 18.53089332989272,
            "lng": 73.83202202989273
          },
          "southwest": {
            "lat": 18.52819367010727,
            "lng": 73.82932237010729
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "Radhey's Bella Casa Girl's Pg and Hostel",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 3072,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/103787108731960794870\">Radhey&#39;s Bella Casa Girl&#39;s Pg and Hostel</a>"
          ],
          "photo_reference": "ATKogpcIu7SXzJntS0bHdJKwAp3Kjj0dw-snNWhmiI15NxlDi7PbirTEeGqNFKiczf8Jh3Whtc9EySLJDuNjlwrHyxlaBrJovkkr9krAn0tsKk4QDBVKTko4Tui1cw77LpDANjPkw_Ol421ZS44f1FmKEqMbHg0beiHeq0A1p6ySFdsn7-lWbf9y_-SQO2oHw_TT8c8C6w0pfEp_ISmyKoRViutbmUN-KrsNAItasA9HM4hEyRZ8MCNFsfW6_ivc75c6kG--ZFi7ZPIQnsRx1xaD41dcy3iO92M40DJMWL-mBjSRAq2V2As",
          "width": 4096
        }
      ],
      "place_id": "ChIJAz_7ewC_wjsRYxDk_NP_PKA",
      "plus_code": {
        "compound_code": "GRHJ+R7 Pune, Maharashtra",
        "global_code": "7JCMGRHJ+R7"
      },
      "rating": 5,
      "reference": "ChIJAz_7ewC_wjsRYxDk_NP_PKA",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 73,
      "vicinity": "S- Wing, Shirole Park, SB Road, Senapti Bapat Road, opposite Subway Outlet, next to Rolls Mania, Model Colony, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5301663,
          "lng": 73.8327243
        },
        "viewport": {
          "northeast": {
            "lat": 18.53144922989272,
            "lng": 73.83408582989273
          },
          "southwest": {
            "lat": 18.52874957010728,
            "lng": 73.83138617010728
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "Nilesh Dongare PG / Hostel",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 402,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/110396243569996697469\">Nilesh Dongare PG / Hostel</a>"
          ],
          "photo_reference": "ATKogpcXq54XHOAWOj9JKMzUchSqdjo5cwQo4GjoWREkCuoGtmjNqBVvufiq9XcvifVTlWcm6SKE1osjL7-bcyJ8e_Sn8ySQepJPE9uP7VK2hlqTgcMkdEeJIhbTUm9sXPfmwAa8HZ6CXdRU3wk-G-XlUkby2SHI9H-EMqhjZ7O42Afd0zknCuMq6AYv-ZzM5PM0nfdeW83EtvGfP7KjL5YyKuQQm9g6xxHV7dON72m0kb1EfkPCUwlqqF4LuYXpsOlhjqPcmpYbbIkzVLN1uzSFdkccUZbT03TtXp-OQMTsaejqaBcylbA",
          "width": 720
        }
      ],
      "place_id": "ChIJj8KhXBe_wjsRDXshQX1EsUk",
      "plus_code": {
        "compound_code": "GRJM+33 Pune, Maharashtra",
        "global_code": "7JCMGRJM+33"
      },
      "rating": 4.5,
      "reference": "ChIJj8KhXBe_wjsRDXshQX1EsUk",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 154,
      "vicinity": "Flat No.3, Dongre Building, 994/1, Model Colony, Wadarvadi, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5266514,
          "lng": 73.8257946
        },
        "viewport": {
          "northeast": {
            "lat": 18.52800132989272,
            "lng": 73.82714302989271
          },
          "southwest": {
            "lat": 18.52530167010728,
            "lng": 73.82444337010726
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/lodging-71.png",
      "icon_background_color": "#909CE1",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/hotel_pinlet",
      "name": "Mahadev PG",
      "photos": [
        {
          "height": 3000,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/108996437078346081591\">H S Lahiri</a>"
          ],
          "photo_reference": "ATKogpeVFvZVRIiw7AYwWwh9u-8uSZAO0kT_53V1xMuX32rFdGGVkAabes9umtHI31CcqrogkDA0sQTKFZab6iypmxg5SsgHrMNLgcbTzmuIrvqzJkmGzPNa-Z-DIZH7L_5oZ3BJL1qRX0w8BV5i1YYGtMDyZQtr5MDLKMSZojuXESwNCR13NyAcce_ZNkzQbRD2waW7MeBTzElDZLwUa_2t4qYCkGWXnhxjjkF8hjs8c7750f6cXDZhcnmXJ4RFLBsaynU03C6SMUqc0y4loapA4Y26qeuqmwNSRwjB0p8DtJ219WaRFgb9QJaKkGWMO8JASDQgIeRlKH3EryXkaIX_tHvDyif9F6gqHZBVcgkxluWn3GSCFhWvlzJZLv1HHkbE_7lVevBlTqJ8wcXrdnI0RpJj7rVJ_oYLq9-PO6DrEr1VurpVXJ8fSDUO1JhI2RkRBw_kUoiCx4v3y2EDi8GS6-7hYsLpXLG_yhQ4HIC7gDZ7ofua9zzNXsBt4xSTUchYUcA3WIGfc1ft4yds6t7uehGJbUFf6TfXzMqzzs6jaseYxW8rWtAg43ZSX3PUrBcWm5Qd-Ri8UxXG6CqAcDAO4O_dc2BzWA",
          "width": 4000
        }
      ],
      "place_id": "ChIJE-qtuHi_wjsRPkH77iBO3U0",
      "plus_code": {
        "compound_code": "GRGG+M8 Pune, Maharashtra",
        "global_code": "7JCMGRGG+M8"
      },
      "rating": 4.8,
      "reference": "ChIJE-qtuHi_wjsRPkH77iBO3U0",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 105,
      "vicinity": "House No. 458, near Sai baba Mandir, Gokhalenagar, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5602381,
          "lng": 73.7878308
        },
        "viewport": {
          "northeast": {
            "lat": 18.56155742989273,
            "lng": 73.78917222989271
          },
          "southwest": {
            "lat": 18.55885777010728,
            "lng": 73.78647257010728
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "SV PG",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 562,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/116688426599368938124\">A Google User</a>"
          ],
          "photo_reference": "ATKogpd8dFvZ2BytJGoAhrXMPSLvBRsEzXOJXaYyaR3tQGE3Gb15h8ZpxNoB6ADEIiiLUxWMvQO_enG17qH0p-uTWYoyZTD08vkiikX6FzCEGZoFVREmEB8bPy3VjjLHNU5fF596-SgCaxSQzsgmkGsKo_troiAQ_LEzVq9gwy1GzKiEjCr27nV_PMtvc3zOpUB8mrEJxXiQ9RnpdDKNkxrCKEeXmurL4wNaIFrZ3vCK9N9nzWJwGRzDzVbMzKRxc7aM9N0LpeesIm3Zsb44SWboOtJ2_uEhBnq0xHahUnmVJE4pSm_rf80",
          "width": 750
        }
      ],
      "place_id": "ChIJHSiFqNO_wjsRB5sOpxQQkh4",
      "plus_code": {
        "compound_code": "HQ6Q+34 Pune, Maharashtra",
        "global_code": "7JCMHQ6Q+34"
      },
      "rating": 3.5,
      "reference": "ChIJHSiFqNO_wjsRB5sOpxQQkh4",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 98,
      "vicinity": "Ram Mandir Chowk, near Jyothi Appliances, Baner Gaon, Baner, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5083792,
          "lng": 73.8142888
        },
        "viewport": {
          "northeast": {
            "lat": 18.50973537989272,
            "lng": 73.81569932989274
          },
          "southwest": {
            "lat": 18.50703572010728,
            "lng": 73.81299967010729
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "Stanza Living Annaba House | Womens PG in Kothrud",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 750,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/113711761791015161623\">A Google User</a>"
          ],
          "photo_reference": "ATKogpc3gYxzEyauBv--bmzEG2tXvx2jFxQN__J7KMiOUoLADfeoIvoXabo2neaBv6wtlAmwYvfkYsYl7MFpnXnBtwMJwefCZ4GfslxS1Eyf4tMNavuoGivJrNETU5oFA8_bWe4m-PqCrS9qQSj4M4fv3jvFqbhueRIbZ9ssQCbPeNxt6Y78T3vXCQ_PvqtSC9W69Tv5M84T-HH4I6aszZTwj4Iswu0HDhl4gVYv2kbXDQ686qCXB8zr5Lo09lkNfIG7-t-2uQccQVB841CsbVOL51XRK2ttjF2ZlBkbqqEtOL7FHRv7CMk",
          "width": 1000
        }
      ],
      "place_id": "ChIJuSaINVO_wjsRXsxqZ7oqfp8",
      "plus_code": {
        "compound_code": "GR57+9P Pune, Maharashtra",
        "global_code": "7JCMGR57+9P"
      },
      "rating": 4.4,
      "reference": "ChIJuSaINVO_wjsRXsxqZ7oqfp8",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 47,
      "vicinity": "Kulkarni Heights 34, Saratha Society, Anand Nagar, Kothrud, Pune"
    },
    {
      "business_status": "OPERATIONAL",
      "geometry": {
        "location": {
          "lat": 18.5191971,
          "lng": 73.8143075
        },
        "viewport": {
          "northeast": {
            "lat": 18.52045612989272,
            "lng": 73.8158035298927
          },
          "southwest": {
            "lat": 18.51775647010728,
            "lng": 73.81310387010727
          }
        }
      },
      "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
      "icon_background_color": "#7B9EB0",
      "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
      "name": "The Foothill Girls Hostel / PG / Accommodation",
      "opening_hours": {
        "open_now": true
      },
      "photos": [
        {
          "height": 4096,
          "html_attributions": [
            "<a href=\"https://maps.google.com/maps/contrib/111026778047623582472\">The Foothill Girls Hostel / PG / Accommodation</a>"
          ],
          "photo_reference": "ATKogpfuQUOAYUaSmj-rv9F7gHgpZ1tgPFrVO0TNDw5hH2l1lKkyW-yxdjD7p6aImJrjgakyJlm8wf-RaTPV4UPU4_Y6onbRoasMzEXoZ2Dl2p8y03RnK6DHtqWtCNpBTItNID04224My7YtxPN2N76QReDC75skL3HIw1ceaMl1RRPqcsCS4mBG1zQYr0kqgg6_6YwQj9PUwKjTWOCUXPU1bQqhZS3jiyZcBWz_uhYcrMwkTUxPqQJoAZasdD572hpzEKyCKKnGL7imccs7JKIzc-MSyMJ__N8jdpHm5dhSQP-5CgTIE0M",
          "width": 1834
        }
      ],
      "place_id": "ChIJD69gItu_wjsRNc6WieEBSy4",
      "plus_code": {
        "compound_code": "GR97+MP Pune, Maharashtra",
        "global_code": "7JCMGR97+MP"
      },
      "rating": 5,
      "reference": "ChIJD69gItu_wjsRNc6WieEBSy4",
      "scope": "GOOGLE",
      "types": [
        "lodging",
        "point_of_interest",
        "establishment"
      ],
      "user_ratings_total": 13,
      "vicinity": "The Foothill Stay - Girls Hostel, 124/1b, inside Vishwashanti Marg, next to Ramdev Mandir, Kishkinda Nagar, Kothrud, Pune"
    }
  ]);



  const [filters, setFilters] = useState({
    gender: '',
    roomType: '',
    maxBudget: '',
  });

  const fetchFilteredPGs = async () => {
    try {
      const { gender, roomType, maxBudget } = filters;
      const params = new URLSearchParams();

      if (gender) params.append('gender', gender);
      if (roomType) params.append('roomType', roomType);
      if (maxBudget) params.append('maxBudget', maxBudget);

      const res = await axios.get(`/pgs/search?${params.toString()}`);
      setPgs(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchFilteredPGs();
  }, [filters]);

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value,
    });
  };

  return (
    <div className="container py-4">
      <h2 className="text-center mb-4">Find Paying Guest Accommodations</h2>
      <div className="w-[280px] bg-white rounded-xl shadow p-4 h-fit sticky top-4">
        <FilterSidebar filters={filters} onFilterChange={handleFilterChange} />
      </div>
      <div className="flex-1">
       <div className="pg-grid">
        {pgs.map((pg) => (

          <PGCard key={pg.place_id} data={pg} onCardClick={()=>{navigate("/pgdetails")}} />
        ))}
      </div>
      </div>

      
    </div>
  );
};

export default FindPG;

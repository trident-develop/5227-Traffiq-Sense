package com.eyecon.glo.data

import com.eyecon.glo.model.IllustrationKind
import com.eyecon.glo.model.LessonContent
import com.eyecon.glo.model.Subtopic
import com.eyecon.glo.model.Topic
import com.eyecon.glo.model.TopicIcon

object TrafficTopicsData {

    val topics: List<Topic> = listOf(
        roadSigns(),
        trafficLights(),
        priorityRules(),
        speedLimits(),
        parkingRules(),
        pedestrianCrossings(),
        laneDiscipline(),
        overtakingRules(),
        intersections(),
        emergencyVehicles(),
        safeDistance(),
        badWeather(),
    )

    fun topicById(id: String): Topic? = topics.firstOrNull { it.id == id }

    fun subtopicById(topicId: String, subtopicId: String): Subtopic? =
        topicById(topicId)?.subtopics?.firstOrNull { it.id == subtopicId }

    private fun lesson(
        explanation: String,
        keyPoints: List<String>,
        illustration: IllustrationKind,
    ) = LessonContent(explanation, keyPoints, illustration)

    private fun roadSigns() = Topic(
        id = "road_signs",
        title = "Road Signs",
        shortDescription = "Recognise the meaning of road signs you see every day.",
        icon = TopicIcon.ROAD_SIGN,
        subtopics = listOf(
            Subtopic("warning", "Warning Signs", lesson(
                "Warning signs alert drivers to a hazard or change in road conditions ahead. They are usually triangular with a red border and give you time to slow down or react.",
                listOf(
                    "Most warning signs are triangular with a red border.",
                    "They appear before the hazard, not at it.",
                    "Reduce speed and stay alert when you see one.",
                    "Common warnings include curves, slippery roads and animals.",
                    "They do not impose a rule – they prepare you to react.",
                ),
                IllustrationKind.WARNING_SIGN,
            )),
            Subtopic("priority", "Priority Signs", lesson(
                "Priority signs tell you who has the right of way at intersections and narrow sections of road. They prevent confusion when several vehicles meet.",
                listOf(
                    "Yield and Stop signs require you to give way.",
                    "Main road signs grant you priority.",
                    "Always check for priority signs before entering an intersection.",
                    "If two priority signs disagree, the most restrictive one wins.",
                    "A Stop sign always means a full stop, even if the road is clear.",
                ),
                IllustrationKind.PRIORITY_SIGN,
            )),
            Subtopic("prohibition", "Prohibition Signs", lesson(
                "Prohibition signs forbid a specific action, such as entering, turning or overtaking. They are typically round with a red border.",
                listOf(
                    "Round shape with red border indicates a prohibition.",
                    "They apply from the sign until cancelled or the next intersection.",
                    "Common examples: No Entry, No Overtaking, No U-turn.",
                    "Ignoring them often leads to a fine and demerit points.",
                    "Some prohibitions are time-limited – read the sub-plate.",
                ),
                IllustrationKind.PROHIBITION_SIGN,
            )),
            Subtopic("mandatory", "Mandatory Signs", lesson(
                "Mandatory signs require drivers to perform a specific action, such as turning in a set direction or using a particular lane. They are round with a blue background.",
                listOf(
                    "Blue circular signs require an action.",
                    "Examples: Turn Left Only, Roundabout, Minimum Speed.",
                    "They cancel at the next intersection or end-of-restriction sign.",
                    "Disobeying a mandatory sign is treated as a moving violation.",
                    "Watch for mandatory direction signs near construction zones.",
                ),
                IllustrationKind.MANDATORY_SIGN,
            )),
            Subtopic("information", "Information Signs", lesson(
                "Information signs provide guidance about routes, services and locations. They help drivers plan and reduce stress on long trips.",
                listOf(
                    "Usually rectangular with a blue or green background.",
                    "Show distances, services or facility locations.",
                    "They do not impose any obligation.",
                    "Read them in advance to stay in the correct lane.",
                    "Tourist destinations often use brown information signs.",
                ),
                IllustrationKind.INFORMATION_SIGN,
            )),
            Subtopic("temporary", "Temporary Signs", lesson(
                "Temporary signs are used during road works, accidents or special events. They override permanent signs in the same area.",
                listOf(
                    "Yellow background marks temporary signs in many countries.",
                    "They take priority over standard signs and markings.",
                    "Speed limits inside work zones are strictly enforced.",
                    "Workers and equipment may appear without warning.",
                    "Once you pass the end-of-works sign, normal rules resume.",
                ),
                IllustrationKind.TEMPORARY_SIGN,
            )),
        ),
    )

    private fun trafficLights() = Topic(
        id = "traffic_lights",
        title = "Traffic Lights",
        shortDescription = "Understand every signal you meet at intersections.",
        icon = TopicIcon.TRAFFIC_LIGHT,
        subtopics = listOf(
            Subtopic("red", "Red Light", lesson(
                "A red light means stop. You must stop before the stop line and remain stopped until the signal changes.",
                listOf(
                    "Stop fully before the stop line, not on it.",
                    "Do not enter the intersection on red.",
                    "Right turn on red is forbidden unless a sign allows it.",
                    "Wait for green – not for the cross traffic to slow.",
                    "Running a red light is a serious offence.",
                ),
                IllustrationKind.TRAFFIC_LIGHT_RED,
            )),
            Subtopic("yellow", "Yellow Light", lesson(
                "Yellow means prepare to stop. You may continue only if stopping safely is impossible.",
                listOf(
                    "Yellow is a warning, not an invitation to accelerate.",
                    "If you can stop safely, you must stop.",
                    "Behind a yellow always comes a red.",
                    "Late entry on yellow can cause T-bone collisions.",
                    "Approach intersections ready to stop, not to gun it.",
                ),
                IllustrationKind.TRAFFIC_LIGHT_YELLOW,
            )),
            Subtopic("green", "Green Light", lesson(
                "Green means you may proceed if the way is clear. It does not give absolute priority.",
                listOf(
                    "Check the intersection is clear before moving.",
                    "Yield to pedestrians already in the crosswalk.",
                    "Yield to vehicles still completing their turn.",
                    "Green arrows give priority for the indicated direction only.",
                    "Do not enter if you cannot clear the intersection.",
                ),
                IllustrationKind.TRAFFIC_LIGHT_GREEN,
            )),
            Subtopic("flashing_yellow", "Flashing Yellow", lesson(
                "A flashing yellow light means caution. The intersection is unregulated and standard right-of-way rules apply.",
                listOf(
                    "Treat the intersection as if there were no signal.",
                    "Apply priority signs or the right-hand rule.",
                    "Slow down and look both ways.",
                    "Often used at night in low-traffic zones.",
                    "Pedestrians may still cross – give way.",
                ),
                IllustrationKind.TRAFFIC_LIGHT_FLASHING,
            )),
            Subtopic("pedestrian", "Pedestrian Signals", lesson(
                "Pedestrian signals coordinate with traffic lights to give walkers a safe window. Drivers must respect them as much as their own lights.",
                listOf(
                    "Walking figure or green man – pedestrians may cross.",
                    "Standing figure or red man – pedestrians must wait.",
                    "Flashing means: finish crossing, do not start.",
                    "Drivers must wait until pedestrians clear the crosswalk.",
                    "Some signals include a count-down timer.",
                ),
                IllustrationKind.PEDESTRIAN_LIGHT,
            )),
            Subtopic("officer", "Traffic Officer Signals", lesson(
                "When a traffic officer directs traffic, their gestures override signals and signs. Watch them carefully and follow exactly.",
                listOf(
                    "Officer's gestures override traffic lights and signs.",
                    "An arm raised vertically means stop for all directions.",
                    "An extended arm to the side stops traffic from that side.",
                    "A waving motion signals you to proceed.",
                    "Make eye contact and acknowledge their command.",
                ),
                IllustrationKind.OFFICER,
            )),
        ),
    )

    private fun priorityRules() = Topic(
        id = "priority",
        title = "Priority Rules",
        shortDescription = "Decide who goes first at every meeting of paths.",
        icon = TopicIcon.PRIORITY,
        subtopics = listOf(
            Subtopic("main_road", "Main Road", lesson(
                "A main-road sign tells you that you have priority through the next intersections until cancelled. Drivers from side roads must yield to you.",
                listOf(
                    "Yellow-and-white diamond marks the main road.",
                    "You keep priority until you see end-of-main-road.",
                    "Still slow down and check side roads.",
                    "Main road status is cancelled by the next priority sign.",
                    "If the main road bends, follow the supplementary plate.",
                ),
                IllustrationKind.MAIN_ROAD,
            )),
            Subtopic("give_way", "Give Way", lesson(
                "Yield (Give Way) signs require you to slow down and let crossing traffic pass without forcing them to brake.",
                listOf(
                    "Triangular sign pointing down means yield.",
                    "Stop only if necessary; otherwise slow and go.",
                    "Make sure you do not force others to brake.",
                    "Yield does not require a complete stop unless unsafe.",
                    "Combine with priority road logic at complex intersections.",
                ),
                IllustrationKind.GIVE_WAY,
            )),
            Subtopic("right_hand", "Right-Hand Rule", lesson(
                "When no signs or lights govern an intersection, the vehicle approaching from the right has priority. Apply this default carefully.",
                listOf(
                    "Used only when no signs and no signals are present.",
                    "Yield to vehicles approaching from your right.",
                    "Make eye contact with other drivers.",
                    "Trams usually have priority over cars regardless of side.",
                    "Slow down well before unmarked intersections.",
                ),
                IllustrationKind.INTERSECTION,
            )),
            Subtopic("roundabouts", "Roundabouts", lesson(
                "At a roundabout the vehicles already inside have priority over those entering. Approach slowly and choose your lane in advance.",
                listOf(
                    "Yield to vehicles already on the roundabout.",
                    "Signal right before exiting.",
                    "Use the inner lane for further exits, the outer for the first.",
                    "Do not stop on the roundabout unless necessary.",
                    "Mini-roundabouts follow the same priority rule.",
                ),
                IllustrationKind.ROUNDABOUT,
            )),
            Subtopic("intersections", "Priority at Intersections", lesson(
                "Most intersection conflicts come from misreading priority. Read signs from far away and confirm with markings on the road.",
                listOf(
                    "Signs and lights override the right-hand rule.",
                    "Stop signs always require a complete stop.",
                    "Watch for priority arrows on combined signs.",
                    "Left-turning vehicles yield to oncoming traffic.",
                    "Drive at a speed that lets you stop if needed.",
                ),
                IllustrationKind.INTERSECTION,
            )),
            Subtopic("public_transport", "Priority for Public Transport", lesson(
                "Buses and trams often have legal priority when leaving stops or using dedicated lanes. Anticipate their movements.",
                listOf(
                    "Yield to buses leaving a marked stop in town.",
                    "Trams almost always have priority over cars.",
                    "Do not block tram tracks at intersections.",
                    "Bus lanes are reserved – stay out unless allowed.",
                    "Pedestrians may cross to or from public transport.",
                ),
                IllustrationKind.PUBLIC_TRANSPORT,
            )),
        ),
    )

    private fun speedLimits() = Topic(
        id = "speed",
        title = "Speed Limits",
        shortDescription = "Match your speed to the road, the rules and the conditions.",
        icon = TopicIcon.SPEED,
        subtopics = listOf(
            Subtopic("city", "City Speed", lesson(
                "Inside built-up areas the default speed limit is usually 50 km/h. Lower limits are common around schools and dense centres.",
                listOf(
                    "Default urban limit is typically 50 km/h.",
                    "Begins at the city-name sign, ends at its crossed version.",
                    "Posted signs may lower the limit further.",
                    "Slow speed protects pedestrians and cyclists.",
                    "Speed cameras are common in city centres.",
                ),
                IllustrationKind.SPEED_CITY,
            )),
            Subtopic("highway", "Highway Speed", lesson(
                "Motorways allow higher speeds because they are separated from cross traffic. Limits vary by country and weather.",
                listOf(
                    "Typical motorway limit is 110–130 km/h.",
                    "Slow vehicles must stay in the right lane.",
                    "Reduce speed in rain or fog, even if posted higher.",
                    "Tailgating at highway speeds is extremely dangerous.",
                    "Always keep a safe braking distance.",
                ),
                IllustrationKind.SPEED_HIGHWAY,
            )),
            Subtopic("school", "School Zones", lesson(
                "School zones have very low speed limits during certain hours. Children may step off the curb without warning.",
                listOf(
                    "Common school zone limit is 30 km/h.",
                    "Active during school hours – read the time plate.",
                    "Look for crossing patrols and yield to them.",
                    "Avoid overtaking inside a school zone.",
                    "Be ready to brake at any moment.",
                ),
                IllustrationKind.SPEED_SCHOOL,
            )),
            Subtopic("residential", "Residential Areas", lesson(
                "Residential or 'home' zones place pedestrians first. Speeds are walking pace and parking is restricted.",
                listOf(
                    "Limit is usually 20 km/h or walking pace.",
                    "Pedestrians may use the full width of the road.",
                    "Park only in marked spaces.",
                    "Children playing have priority.",
                    "Leave the zone via the marked exit sign.",
                ),
                IllustrationKind.SPEED_RESIDENTIAL,
            )),
            Subtopic("weather", "Weather-Based Speed", lesson(
                "Posted limits assume good conditions. In rain, fog, snow or ice you must drive slower – even if no sign tells you so.",
                listOf(
                    "Many countries lower motorway limits in rain.",
                    "Fog can require speeds of 50 km/h or less.",
                    "Ice doubles braking distance – cut speed sharply.",
                    "Drive at a speed that matches visibility.",
                    "If in doubt, slow down further.",
                ),
                IllustrationKind.SPEED_WEATHER,
            )),
            Subtopic("speed_signs", "Speed Limit Signs", lesson(
                "Speed limit signs are round with a red border and a number. End-of-limit signs cancel them.",
                listOf(
                    "Round red-bordered sign sets the new maximum.",
                    "Sign with diagonal stripes ends the limit.",
                    "Limits also end at the next city or highway sign.",
                    "Recommended speed signs are square and blue.",
                    "Speed advisories below warning signs are not legal limits.",
                ),
                IllustrationKind.SPEED_SIGN,
            )),
        ),
    )

    private fun parkingRules() = Topic(
        id = "parking",
        title = "Parking Rules",
        shortDescription = "Park legally and safely without blocking traffic.",
        icon = TopicIcon.PARKING,
        subtopics = listOf(
            Subtopic("no_parking", "No Parking Areas", lesson(
                "A no-parking sign forbids leaving a vehicle there, but you may stop briefly for loading or letting passengers out.",
                listOf(
                    "Round blue sign with one red diagonal = no parking.",
                    "You may stop briefly to drop off passengers.",
                    "Loading and unloading is usually allowed.",
                    "Limit applies until cancelled or the next intersection.",
                    "Time limits on the sub-plate restrict the rule.",
                ),
                IllustrationKind.NO_PARKING,
            )),
            Subtopic("no_stopping", "No Stopping Areas", lesson(
                "A no-stopping sign forbids any voluntary stop. The vehicle must keep moving except in an emergency.",
                listOf(
                    "Round blue sign with two red diagonals = no stopping.",
                    "No drop-off, no loading, no waiting.",
                    "Emergency stops are still allowed.",
                    "Often used near intersections and tunnels.",
                    "Penalties are usually higher than no-parking.",
                ),
                IllustrationKind.NO_STOPPING,
            )),
            Subtopic("crosswalk", "Parking Near Crosswalks", lesson(
                "Stopping too close to a crosswalk hides pedestrians from approaching drivers. Local laws set a minimum distance.",
                listOf(
                    "Keep 5 m clear before a pedestrian crossing.",
                    "Never stop on the crossing itself.",
                    "Tall vehicles must park further away.",
                    "Same rule applies before bus stops in many countries.",
                    "Visibility for kids is key – give the crossing space.",
                ),
                IllustrationKind.PARK_CROSSWALK,
            )),
            Subtopic("hill", "Parking on Hills", lesson(
                "On a slope, turn the wheels so the car will roll into the kerb if the brakes fail. Always engage the parking brake.",
                listOf(
                    "Engage the parking brake every time.",
                    "Facing downhill: turn wheels into the kerb.",
                    "Facing uphill with kerb: turn wheels away from kerb.",
                    "Facing uphill without kerb: turn wheels into the road edge.",
                    "Leave the car in gear or in 'P'.",
                ),
                IllustrationKind.PARK_HILL,
            )),
            Subtopic("parallel", "Parallel Parking Basics", lesson(
                "Parallel parking is the most common urban manoeuvre. A reference-point routine makes it stress-free.",
                listOf(
                    "Find a space 1.5× your car length.",
                    "Pull alongside the car in front, mirrors aligned.",
                    "Reverse with full lock until your front clears the rear car.",
                    "Straighten the wheels and slide back into the spot.",
                    "Adjust forward to centre the car.",
                ),
                IllustrationKind.PARK_PARALLEL,
            )),
            Subtopic("disabled", "Disabled Parking Spaces", lesson(
                "Disabled parking spaces are reserved for permit holders. Misuse carries a heavy fine and disrupts people who need access.",
                listOf(
                    "Marked with a wheelchair symbol on sign and pavement.",
                    "Only valid permit holders may park there.",
                    "Permit must be visible behind the windscreen.",
                    "Spaces are wider for wheelchair access.",
                    "Penalties are much higher than regular parking.",
                ),
                IllustrationKind.PARK_DISABLED,
            )),
        ),
    )

    private fun pedestrianCrossings() = Topic(
        id = "pedestrians",
        title = "Pedestrian Crossings",
        shortDescription = "Protect the most vulnerable road users.",
        icon = TopicIcon.PEDESTRIAN,
        subtopics = listOf(
            Subtopic("zebra", "Zebra Crossings", lesson(
                "Zebra crossings give priority to pedestrians who have stepped onto, or are clearly waiting at, the markings.",
                listOf(
                    "Slow down on approach, even if no one is waiting.",
                    "Stop for any pedestrian on the crossing.",
                    "Do not overtake a vehicle stopped at a zebra.",
                    "Wait until pedestrians fully clear the road.",
                    "At night dipped headlights help you see them earlier.",
                ),
                IllustrationKind.ZEBRA,
            )),
            Subtopic("light_crossing", "Traffic Light Crossings", lesson(
                "Where pedestrian lights are present, both drivers and pedestrians must follow them strictly.",
                listOf(
                    "Stop on red even if no pedestrians are visible.",
                    "Pedestrians may not cross on a red figure.",
                    "Wait for the road to clear before moving on green.",
                    "Flashing means the cycle is ending.",
                    "Do not block the crossing while waiting.",
                ),
                IllustrationKind.LIGHT_CROSSING,
            )),
            Subtopic("school_crossing", "School Crossings", lesson(
                "School crossings combine markings, signs and often a crossing patrol. Caution must be extreme.",
                listOf(
                    "Speed limit drops, often to 30 km/h.",
                    "Obey the patrol's stop sign instantly.",
                    "Children may dart out unpredictably.",
                    "No overtaking near a school crossing.",
                    "Phones and distractions are extremely risky here.",
                ),
                IllustrationKind.SCHOOL_CROSSING,
            )),
            Subtopic("priority", "Pedestrian Priority", lesson(
                "Beyond signs, drivers should anticipate pedestrian movement and yield in shared zones, near schools and at residential entrances.",
                listOf(
                    "Pedestrians always have priority on crossings.",
                    "Yield to people leaving public transport.",
                    "Anticipate movement near shops and bus stops.",
                    "In residential zones, pedestrians use the full road.",
                    "Approach with hand off the gas, near the brake.",
                ),
                IllustrationKind.PEDESTRIAN_PRIORITY,
            )),
            Subtopic("cyclist", "Cyclist Crossings", lesson(
                "Cyclist crossings are marked with red or green strips. Cyclists may move much faster than pedestrians.",
                listOf(
                    "Watch for red or green road markings.",
                    "Cyclists keep their pace – do not assume they will stop.",
                    "Look over your shoulder before turning.",
                    "Allow at least 1.5 m when passing a cyclist.",
                    "Combined cyclist-pedestrian crossings need extra care.",
                ),
                IllustrationKind.CYCLIST_CROSSING,
            )),
            Subtopic("unsafe", "Unsafe Crossing Situations", lesson(
                "Some crossings hide pedestrians behind tall vehicles, parked cars or poor lighting. Recognise them and slow further.",
                listOf(
                    "Tall vehicles can hide a child completely.",
                    "Parked cars within 5 m of the crossing reduce visibility.",
                    "Poor street lighting raises the risk at night.",
                    "Wet markings can be slippery for two-wheelers.",
                    "If unsure, stop and wave the pedestrian through.",
                ),
                IllustrationKind.UNSAFE_CROSSING,
            )),
        ),
    )

    private fun laneDiscipline() = Topic(
        id = "lanes",
        title = "Lane Discipline",
        shortDescription = "Use the right lane, in the right way, at the right time.",
        icon = TopicIcon.LANE,
        subtopics = listOf(
            Subtopic("markings", "Lane Markings", lesson(
                "Road markings tell you what each lane is for and what manoeuvres are allowed.",
                listOf(
                    "White lines separate lanes in the same direction.",
                    "Yellow lines often indicate special rules.",
                    "Pre-select the correct lane well in advance.",
                    "Markings are reinforced by signs above the road.",
                    "Worn markings still apply – follow what you can see.",
                ),
                IllustrationKind.LANE_MARKINGS,
            )),
            Subtopic("change", "Changing Lanes", lesson(
                "A safe lane change uses mirrors, blind spot check and indicator – every time.",
                listOf(
                    "Mirror, signal, blind-spot, manoeuvre.",
                    "Signal early enough for others to react.",
                    "Never force your way in.",
                    "Check the mirror again after changing.",
                    "Avoid lane changes in intersections.",
                ),
                IllustrationKind.LANE_CHANGE,
            )),
            Subtopic("bus_lane", "Bus Lanes", lesson(
                "Bus lanes are reserved for public transport and sometimes for taxis or bicycles. Misuse blocks transit and is fined.",
                listOf(
                    "Marked with the word BUS or a transit symbol.",
                    "Cars are not allowed unless a sign permits it.",
                    "Cyclists and taxis may share the lane in some cities.",
                    "Cameras frequently enforce bus lanes.",
                    "Crossing the bus lane to turn is allowed at intersections.",
                ),
                IllustrationKind.BUS_LANE,
            )),
            Subtopic("turn", "Turning Lanes", lesson(
                "A dedicated turning lane keeps through traffic moving while turning vehicles wait safely.",
                listOf(
                    "Enter the turn lane only when committed to turn.",
                    "Do not change your mind once inside.",
                    "Yield to oncoming traffic when turning left.",
                    "Some left-turn lanes have a dedicated arrow signal.",
                    "Do not stop in a turning lane unless turning.",
                ),
                IllustrationKind.TURN_LANE,
            )),
            Subtopic("arrows", "Lane Arrows", lesson(
                "Arrows painted on the lane show the only directions allowed from that lane.",
                listOf(
                    "Each arrow shows a permitted direction.",
                    "Multiple arrows mean any of those choices.",
                    "Pre-select the correct lane before arrows appear.",
                    "Going against the arrow is a violation.",
                    "Confirm with overhead lane signs.",
                ),
                IllustrationKind.LANE_ARROWS,
            )),
            Subtopic("solid_broken", "Solid and Broken Lines", lesson(
                "Solid white or yellow lines forbid crossing; broken lines allow lane changes when safe.",
                listOf(
                    "Broken line: lane change is permitted.",
                    "Solid line: do not cross or straddle.",
                    "Two solid lines side by side: absolute no-cross.",
                    "Solid + broken: only the side with broken may cross.",
                    "Crossing a solid line is a moving violation.",
                ),
                IllustrationKind.SOLID_BROKEN,
            )),
        ),
    )

    private fun overtakingRules() = Topic(
        id = "overtaking",
        title = "Overtaking Rules",
        shortDescription = "Pass other vehicles only when it is fully safe.",
        icon = TopicIcon.OVERTAKE,
        subtopics = listOf(
            Subtopic("safe", "Safe Overtaking", lesson(
                "A safe overtake demands clear visibility, enough power and a return-to-lane plan.",
                listOf(
                    "Check mirrors and blind spot before pulling out.",
                    "Signal, then move out smoothly.",
                    "Pass quickly, do not linger alongside.",
                    "Re-enter only when you can see the overtaken car in your mirror.",
                    "Cancel the indicator after returning to lane.",
                ),
                IllustrationKind.OVERTAKE_SAFE,
            )),
            Subtopic("forbidden", "When Overtaking Is Forbidden", lesson(
                "Many situations forbid overtaking, even without a sign. Recognise them so you do not need to ask.",
                listOf(
                    "On solid centre lines.",
                    "Inside or just before pedestrian crossings.",
                    "On hills with reduced visibility.",
                    "In tunnels unless extra lanes are marked.",
                    "Where signs prohibit it.",
                ),
                IllustrationKind.OVERTAKE_FORBIDDEN,
            )),
            Subtopic("cyclist", "Overtaking Cyclists", lesson(
                "Cyclists are vulnerable. Give them space, be patient and never squeeze them against the kerb.",
                listOf(
                    "Leave at least 1.5 m of side clearance.",
                    "Wait for a clear, straight stretch.",
                    "Do not honk close behind them.",
                    "Pass smoothly – no acceleration burst.",
                    "Re-enter only when fully clear.",
                ),
                IllustrationKind.OVERTAKE_CYCLIST,
            )),
            Subtopic("hill", "Overtaking on Hills", lesson(
                "Hills hide oncoming traffic. Overtaking before the crest is one of the most dangerous things you can do.",
                listOf(
                    "Never overtake near the top of a hill.",
                    "Wait until you can see far past the crest.",
                    "Slow vehicles often have a dedicated lane uphill.",
                    "Do not assume the other side is empty.",
                    "If unsure, do not overtake.",
                ),
                IllustrationKind.OVERTAKE_HILL,
            )),
            Subtopic("crossing", "Overtaking Near Crossings", lesson(
                "Pedestrian and railway crossings deserve full attention. Overtaking near them is forbidden in most countries.",
                listOf(
                    "Do not overtake within the approach to a zebra.",
                    "Do not overtake at railway crossings.",
                    "If the car ahead stops, assume someone is crossing.",
                    "Hold position, wait, then proceed.",
                    "Penalties for overtaking here are severe.",
                ),
                IllustrationKind.OVERTAKE_CROSSING,
            )),
            Subtopic("return", "Returning to Lane", lesson(
                "Returning to your lane is the part most drivers misjudge. Be sure you can fully see the car behind you.",
                listOf(
                    "Check the mirror, not just over the shoulder.",
                    "Both headlights of the overtaken car must be visible.",
                    "Indicate your return.",
                    "Move back smoothly, without cutting in.",
                    "Maintain speed; do not slow down sharply.",
                ),
                IllustrationKind.OVERTAKE_RETURN,
            )),
        ),
    )

    private fun intersections() = Topic(
        id = "intersections",
        title = "Intersections",
        shortDescription = "Read the geometry, the signs and the lights at every junction.",
        icon = TopicIcon.INTERSECTION,
        subtopics = listOf(
            Subtopic("controlled", "Controlled Intersections", lesson(
                "A controlled intersection has signals, signs or an officer. Follow them in this order: officer, light, sign, marking.",
                listOf(
                    "Officer instructions override everything.",
                    "Lights override signs and markings.",
                    "Signs override painted markings.",
                    "Confirm green is for your lane, not the next one.",
                    "Stop fully behind the line.",
                ),
                IllustrationKind.INT_CONTROLLED,
            )),
            Subtopic("uncontrolled", "Uncontrolled Intersections", lesson(
                "An uncontrolled intersection is governed by the right-hand rule. Slow down and prepare to yield.",
                listOf(
                    "Right-hand rule applies.",
                    "Always slow down – speeds drop visibility.",
                    "Trams have priority over cars.",
                    "Pedestrians keep their crossing rights.",
                    "Make eye contact whenever possible.",
                ),
                IllustrationKind.INT_UNCONTROLLED,
            )),
            Subtopic("turn_left", "Turning Left", lesson(
                "Left turns cross oncoming traffic and demand patience. Stop in the centre if needed and complete the turn after the gap.",
                listOf(
                    "Yield to oncoming vehicles going straight.",
                    "Yield to pedestrians on the road you turn into.",
                    "Use the dedicated left-turn lane if present.",
                    "Wait inside the intersection only if safe and legal.",
                    "Watch for cyclists in your blind spot.",
                ),
                IllustrationKind.TURN_LEFT,
            )),
            Subtopic("turn_right", "Turning Right", lesson(
                "Right turns are simpler but still need awareness of cyclists and pedestrians cutting across your path.",
                listOf(
                    "Approach in the right-most legal lane.",
                    "Indicate well before the turn.",
                    "Yield to pedestrians on the new street.",
                    "Watch for cyclists on your right.",
                    "Avoid sharp turns – swing to maintain lane.",
                ),
                IllustrationKind.TURN_RIGHT,
            )),
            Subtopic("roundabouts", "Roundabouts", lesson(
                "Roundabouts simplify intersections but require correct lane selection and clear signalling.",
                listOf(
                    "Yield on entry; signal right on exit.",
                    "Choose the lane that matches your exit.",
                    "Do not change lanes inside if avoidable.",
                    "Cyclists may take the centre of a small roundabout.",
                    "Trucks need extra space – do not crowd them.",
                ),
                IllustrationKind.ROUNDABOUT,
            )),
            Subtopic("stop_line", "Stop Line Rules", lesson(
                "Stop lines mark exactly where you must stop. Crossing them on red is one of the most expensive violations.",
                listOf(
                    "Stop with bumper before the line.",
                    "If no line, stop before the crosswalk.",
                    "If no crosswalk, stop where you can see clearly.",
                    "Do not creep into the intersection while waiting.",
                    "Some lines apply only on red, others always.",
                ),
                IllustrationKind.STOP_LINE,
            )),
        ),
    )

    private fun emergencyVehicles() = Topic(
        id = "emergency",
        title = "Emergency Vehicles",
        shortDescription = "Clear the way for those racing to save lives.",
        icon = TopicIcon.EMERGENCY,
        subtopics = listOf(
            Subtopic("ambulance", "Ambulance", lesson(
                "An ambulance with sirens and lights has near-absolute priority. Yield calmly without panicking.",
                listOf(
                    "Slow down and pull to the right.",
                    "Stop if needed – do not block the route.",
                    "Do not run a red light to clear space.",
                    "Watch for a second ambulance behind it.",
                    "Resume only after it has fully passed.",
                ),
                IllustrationKind.AMBULANCE,
            )),
            Subtopic("police", "Police Car", lesson(
                "Police vehicles use lights and sirens for emergencies. Following them too closely is illegal.",
                listOf(
                    "Yield as for any emergency vehicle.",
                    "Do not follow a responding police car.",
                    "If signalled to stop, pull over safely.",
                    "Keep hands visible during a stop.",
                    "Provide documents calmly.",
                ),
                IllustrationKind.POLICE,
            )),
            Subtopic("fire", "Fire Truck", lesson(
                "Fire trucks are large, slow to brake and unforgiving. Give them extra room.",
                listOf(
                    "Do not park within 5 m of a fire hydrant.",
                    "Pull right and stop for sirens.",
                    "Keep distance – fire trucks brake slowly.",
                    "Watch for hoses crossing the road at incidents.",
                    "Never drive over a fire hose.",
                ),
                IllustrationKind.FIRE_TRUCK,
            )),
            Subtopic("give_way", "Giving Way", lesson(
                "Yielding to emergency vehicles must be predictable. Sudden moves are as dangerous as not yielding.",
                listOf(
                    "Slow and signal so others see your move.",
                    "Pull right unless that blocks the route.",
                    "Use the shoulder only as a last resort.",
                    "Do not cross solid lines unless safe.",
                    "Resume traffic flow gradually.",
                ),
                IllustrationKind.GIVE_WAY_EMERGENCY,
            )),
            Subtopic("sirens", "Sirens and Flashing Lights", lesson(
                "Different combinations of lights and sirens mean different things. Listen and look before reacting.",
                listOf(
                    "Sirens + lights = active emergency, full priority.",
                    "Lights only = on scene, slow and cautious.",
                    "Two-tone vs wail signals different agencies.",
                    "Identify the direction before moving.",
                    "Lower your music to hear sirens earlier.",
                ),
                IllustrationKind.SIRENS,
            )),
            Subtopic("reaction", "Safe Reaction", lesson(
                "A safe reaction is calm, predictable and respects nearby drivers.",
                listOf(
                    "Check mirrors before changing lanes.",
                    "Avoid braking sharply – signal first.",
                    "Use indicators, not just steering.",
                    "Keep moving slowly if stopping is unsafe.",
                    "Wait until the vehicle clears, then move on.",
                ),
                IllustrationKind.SAFE_REACTION,
            )),
        ),
    )

    private fun safeDistance() = Topic(
        id = "distance",
        title = "Safe Distance",
        shortDescription = "Time, not metres, decides if you can stop in time.",
        icon = TopicIcon.DISTANCE,
        subtopics = listOf(
            Subtopic("two_second", "Two-Second Rule", lesson(
                "Pick a fixed point ahead. When the car in front passes it, count two seconds. If you reach it sooner, you are too close.",
                listOf(
                    "Two seconds is the dry-road minimum.",
                    "Pick a fixed reference like a post.",
                    "Speed-independent – it scales with you.",
                    "Larger gaps for poor visibility.",
                    "More gap also means smoother driving.",
                ),
                IllustrationKind.TWO_SECOND,
            )),
            Subtopic("braking", "Braking Distance", lesson(
                "Braking distance grows with the square of speed. Doubling your speed quadruples the metres needed to stop.",
                listOf(
                    "Reaction time adds about a second of travel.",
                    "Wet roads double braking distance.",
                    "Worn tyres add metres at every speed.",
                    "ABS reduces stopping distance only on bad surfaces.",
                    "Slower speed is the cheapest safety upgrade.",
                ),
                IllustrationKind.BRAKING,
            )),
            Subtopic("wet", "Wet Road Distance", lesson(
                "On a wet road, friction drops sharply. Increase your following gap to at least four seconds.",
                listOf(
                    "Use the four-second rule in rain.",
                    "Look out for standing water.",
                    "Avoid hard braking; ease off the accelerator.",
                    "Wipers and lights improve visibility.",
                    "Aquaplaning needs a calm steering response.",
                ),
                IllustrationKind.WET_DISTANCE,
            )),
            Subtopic("night", "Night Driving Distance", lesson(
                "At night you can only react to what you can see. Match speed and gap to your headlights' reach.",
                listOf(
                    "Drive within the range of your headlights.",
                    "Use high beams when no traffic is around.",
                    "Dim early to avoid dazzling oncoming drivers.",
                    "Increase the gap to four seconds.",
                    "Take regular breaks – fatigue worsens at night.",
                ),
                IllustrationKind.NIGHT_DISTANCE,
            )),
            Subtopic("trucks", "Distance Behind Trucks", lesson(
                "Trucks block your view and can drop debris. Their wake at high speed can be unsettling.",
                listOf(
                    "Hang back so the driver can see you.",
                    "Beware of stones thrown by tyres.",
                    "Trucks brake more slowly than cars.",
                    "Plan overtakes well in advance.",
                    "Avoid sitting in their blind spots.",
                ),
                IllustrationKind.TRUCK_DISTANCE,
            )),
            Subtopic("tailgating", "Tailgating Risks", lesson(
                "Tailgating is the cause of most rear-end crashes. Even a small obstacle becomes unavoidable.",
                listOf(
                    "Tailgating shortens your reaction window.",
                    "It rarely makes the trip faster.",
                    "Ease off and let the situation calm.",
                    "If tailgated, change lane and let them pass.",
                    "Stay predictable – do not brake-check.",
                ),
                IllustrationKind.TAILGATING,
            )),
        ),
    )

    private fun badWeather() = Topic(
        id = "weather",
        title = "Driving in Bad Weather",
        shortDescription = "Adapt your driving to rain, fog, snow and ice.",
        icon = TopicIcon.WEATHER,
        subtopics = listOf(
            Subtopic("rain", "Rain", lesson(
                "Rain reduces grip, visibility and reaction time. Slow down at the first drops.",
                listOf(
                    "Turn on dipped headlights, not high beams.",
                    "Increase the gap to four seconds.",
                    "Avoid puddles you cannot see through.",
                    "Watch out for cyclists on slippery markings.",
                    "Brake gently and earlier than usual.",
                ),
                IllustrationKind.RAIN,
            )),
            Subtopic("fog", "Fog", lesson(
                "Fog hides everything, including stationary obstacles. Use proper fog lights and stay calm.",
                listOf(
                    "Turn on fog lights when visibility drops below 50 m.",
                    "Switch them off when visibility returns.",
                    "Drive slowly and follow the right-edge line.",
                    "Increase distance and avoid overtaking.",
                    "Listen as well as look – open the window briefly.",
                ),
                IllustrationKind.FOG,
            )),
            Subtopic("snow", "Snow", lesson(
                "Snow demands smooth inputs. Sharp braking, steering or accelerating breaks grip easily.",
                listOf(
                    "Pull away in the highest gear that holds.",
                    "Brake early, gently and in a straight line.",
                    "Use winter tyres if possible.",
                    "Clear the whole car – not just a peephole.",
                    "Carry a scraper, gloves and water.",
                ),
                IllustrationKind.SNOW,
            )),
            Subtopic("ice", "Ice", lesson(
                "Ice can cut grip to almost nothing. Black ice on a clear morning is the most dangerous.",
                listOf(
                    "Reduce speed dramatically.",
                    "Avoid sudden inputs – brake, steer or accelerate.",
                    "Bridges freeze first.",
                    "If you slide, look where you want to go.",
                    "Ease off the gas instead of braking.",
                ),
                IllustrationKind.ICE,
            )),
            Subtopic("low_vis", "Low Visibility", lesson(
                "Low visibility can come from rain, fog, snow or dust. Make yourself easy to see and slow down.",
                listOf(
                    "Switch on headlights, even in daytime.",
                    "Use fog lights only when needed.",
                    "Increase following distance.",
                    "Avoid abrupt lane changes.",
                    "If visibility drops to nearly zero, stop safely.",
                ),
                IllustrationKind.LOW_VISIBILITY,
            )),
            Subtopic("aquaplaning", "Aquaplaning", lesson(
                "Aquaplaning is when tyres ride on a film of water. Recovery is calm: steady wheel, ease off the gas.",
                listOf(
                    "It happens at speed in standing water.",
                    "Do not brake or steer sharply.",
                    "Hold the wheel straight and ease off.",
                    "Once grip returns, brake gently.",
                    "Worn tyres dramatically increase the risk.",
                ),
                IllustrationKind.AQUAPLANING,
            )),
        ),
    )
}

import java.util.*;

public class PhishGuardAI {

    static String[] scamKeywords = {
            "urgent", "win", "prize", "click", "otp", "password",
            "bank", "verify", "limited offer", "free", "lottery", "account blocked"
    };

    static List<String> history = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n======================================");
            System.out.println("   PHISHGUARD AI - Scam Detector");
            System.out.println("======================================");
            System.out.println("1. Analyze Message");
            System.out.println("2. View History");
            System.out.println("3. Exit");

            System.out.print("\nEnter choice: ");
            int choice;

            // Handle invalid input
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            if (choice == 1) {

                System.out.print("\nEnter Message: ");
                String input = sc.nextLine().toLowerCase();

                int score = 0;
                List<String> reasons = new ArrayList<>();

                // Keyword Detection
                for (String word : scamKeywords) {
                    if (input.contains(word)) {
                        score += 2;
                        reasons.add("Suspicious keyword: " + word);
                    }
                }

                // Link Detection
                if (input.contains("http") || input.contains("www")) {
                    score += 3;
                    reasons.add("Contains suspicious link");
                }

                // Urgency Detection
                if (input.contains("immediately") || input.contains("now")) {
                    score += 2;
                    reasons.add("Creates urgency");
                }

                // Multiple exclamation marks
                if (input.contains("!!!")) {
                    score += 1;
                    reasons.add("Excessive punctuation");
                }

                System.out.println("\n📊 AI Analyzing Message...\n");

                String result;
                if (score >= 7) {
                    result = "🚨 HIGH RISK: SCAM DETECTED";
                } else if (score >= 4) {
                    result = "⚠️ MEDIUM RISK: Suspicious Message";
                } else {
                    result = "✅ LOW RISK: Likely Safe";
                }

                int confidence = Math.min(score * 10, 100);

                System.out.println(result);
                System.out.println("Confidence Score: " + confidence + "%");

                System.out.println("\n🧠 Detection Reasons:");
                if (reasons.isEmpty()) {
                    System.out.println("No suspicious patterns detected");
                } else {
                    for (String r : reasons) {
                        System.out.println("- " + r);
                    }
                }

                // Save history
                history.add("Message: " + input);
                history.add("Result: " + result + " (" + confidence + "%)");
                history.add("-----------------------------");

            } else if (choice == 2) {

                System.out.println("\n📜 Analysis History:\n");

                if (history.isEmpty()) {
                    System.out.println("No history available.");
                } else {
                    for (String h : history) {
                        System.out.println(h);
                    }
                }

            } else if (choice == 3) {
                System.out.println("\nExiting... Thank you!");
                break;
            } else {
                System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
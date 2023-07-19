import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        HelperKt.doInitKoin()
        HelperKt.doInitNapier()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
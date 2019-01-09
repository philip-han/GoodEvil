import UIKit
import app
import shared

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = Proxy().proxyHello()
        GiphyApi().good(
            callback: {
                (result: String) -> KotlinUnit in
                print(result)
                return KotlinUnit()
            }
        )
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}

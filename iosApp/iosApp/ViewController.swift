import UIKit
import shared
import SDWebImage

class ViewController: UIViewController {
    
    /*
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return (images.data?.count)!
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> GiphyCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "giphyCell", for: indexPath)
        images.data?.
        (cell as! GiphyCell).displayContent(image: images.data?[0])
    } */
    
    
    // MARK: Properties
    @IBOutlet weak var goodButton: UIButton!
    @IBOutlet weak var evilButton: UIButton!
    @IBOutlet weak var imageView: UIImageView!
    //private var images: GiphyApi.SearchResult!

    private func handler() -> (GiphyApi.SearchResult) -> KotlinUnit {
        return {
            (result: GiphyApi.SearchResult) -> KotlinUnit  in
            for d in result.data! {
                print("type: \(d.type) id: \(d.id) url: \(d.images.fixedWidthDownSampled.url)")
            }
            self.imageView.sd_setImage(with: URL(string: result.data![0].url))
            return KotlinUnit()
        }
    }
    
    private let callback: (GiphyApi.SearchResult) -> KotlinUnit = {
        (result: GiphyApi.SearchResult) -> KotlinUnit  in
        for d in result.data! {
            print("type: \(d.type) id: \(d.id) url: \(d.images.fixedWidthDownSampled.url)")
        }
        return KotlinUnit()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    @IBAction func pressed(_ sender: UIButton) {
        GiphyApi().good(callback: handler())
    }
    
    @IBAction func evilPressed(_ sender: UIButton) {
        GiphyApi().evil(callback: handler())
    }
}
